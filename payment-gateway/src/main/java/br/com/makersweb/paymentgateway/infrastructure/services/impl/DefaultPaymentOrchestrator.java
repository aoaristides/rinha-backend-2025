package br.com.makersweb.paymentgateway.infrastructure.services.impl;

import br.com.makersweb.paymentgateway.domain.Payment;
import br.com.makersweb.paymentgateway.domain.ProcessorAttempt;
import br.com.makersweb.paymentgateway.domain.enums.PaymentStatus;
import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;
import br.com.makersweb.paymentgateway.domain.models.PaymentResponse;
import br.com.makersweb.paymentgateway.domain.repositories.PaymentRepository;
import br.com.makersweb.paymentgateway.domain.repositories.ProcessorAttemptRepository;
import br.com.makersweb.paymentgateway.infrastructure.services.PaymentOrchestrator;
import br.com.makersweb.paymentgateway.infrastructure.services.ProcessorClient;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

/**
 * @author aaristides
 */
@Service
@Slf4j
public class DefaultPaymentOrchestrator implements PaymentOrchestrator {

    private final PaymentRepository paymentRepo;
    private final ProcessorAttemptRepository attemptRepo;
    private final HealthProbeService health;
    private final ProcessorClient defaultClient;
    private final ProcessorClient fallbackClient;
    private final CircuitBreaker cbDefault;
    private final CircuitBreaker cbFallback;
    private final Bulkhead bhDefault;
    private final Bulkhead bhFallback;

    public DefaultPaymentOrchestrator(PaymentRepository paymentRepo,
                                      ProcessorAttemptRepository attemptRepo,
                                      HealthProbeService health,
                                      @Qualifier("defaultClient") ProcessorClient defaultClient,
                                      @Qualifier("fallbackClient") ProcessorClient fallbackClient,
                                      CircuitBreaker cbDefault,
                                      CircuitBreaker cbFallback,
                                      Bulkhead defaultBulkhead,
                                      Bulkhead fallbackBulkhead) {
        this.paymentRepo = paymentRepo;
        this.attemptRepo = attemptRepo;
        this.health = health;
        this.defaultClient = defaultClient;
        this.fallbackClient = fallbackClient;
        this.cbDefault = cbDefault;
        this.cbFallback = cbFallback;
        this.bhDefault = defaultBulkhead;
        this.bhFallback = fallbackBulkhead;
    }

    @Override
    public PaymentResponse process(final BigDecimal amount, final String correlationId) {
        log.info("Init method process to correlationId - {}", correlationId);
        // Idempotência local
        var existing = paymentRepo.findByCorrelationId(correlationId).orElse(null);
        if (existing != null) {
            return toResponse(existing);
        }

        var payment = new Payment();
        payment.setAmount(amount);
        payment.setCorrelationId(correlationId);
        payment.setStatus(PaymentStatus.FAILED);
        payment.setRequestedAt(Instant.now());
        paymentRepo.save(payment);

        var dh = health.defaultHealth();
        var fh = health.fallbackHealth();

        List<ProcessorClient> order = decideOrder(dh, fh);

        for (ProcessorClient pc : order) {
            var cb = pc.type() == ProcessorType.DEFAULT ? cbDefault : cbFallback;
            var bh = pc.type() == ProcessorType.DEFAULT ? bhDefault : bhFallback;

            long start = System.currentTimeMillis();
            try {
                var result = Bulkhead
                        .decorateSupplier(bh, CircuitBreaker
                                .decorateSupplier(cb, () -> pc.pay(correlationId, amount).block()))
                        .get();

                var attempt = new ProcessorAttempt();
                attempt.setPaymentId(payment.getId());
                attempt.setProcessor(pc.type());
                attempt.setHttpStatus(result.httpStatus());
                attempt.setSuccess(result.success());
                attempt.setLatencyMs(System.currentTimeMillis() - start);
                attemptRepo.save(attempt);

                if (result.success()) {
                    fillSuccess(payment, pc);
                    paymentRepo.save(payment);
                    return toResponse(payment);
                }

            } catch (Exception e) {
                var attempt = new ProcessorAttempt();
                attempt.setPaymentId(payment.getId());
                attempt.setProcessor(pc.type());
                attempt.setSuccess(false);
                attempt.setError(e.getClass().getSimpleName());
                attempt.setLatencyMs(System.currentTimeMillis() - start);
                attemptRepo.save(attempt);
                log.error("Error process api.", e);
            }
        }

        payment.setStatus(PaymentStatus.FAILED);
        paymentRepo.save(payment);
        return toResponse(payment);
    }

    private List<ProcessorClient> decideOrder(HealthProbeService.Health dh, HealthProbeService.Health fh) {
        log.info("Init method decideOrder.");
        boolean defaultViavel = dh != null && dh.healthy() && dh.minProcessingMs() < 5000;
        boolean fallbackViavel = fh != null && fh.healthy() && fh.minProcessingMs() < 5000;

        if (defaultViavel && !fallbackViavel) return List.of(defaultClient, fallbackClient);
        if (!defaultViavel && fallbackViavel) return List.of(fallbackClient, defaultClient);
        return List.of(defaultClient, fallbackClient);
    }

    private void fillSuccess(Payment p, ProcessorClient pc) {
        var fee = p.getAmount().multiply(pc.feePercent()).setScale(2, RoundingMode.HALF_UP);
        var net = p.getAmount().subtract(fee).setScale(2, RoundingMode.HALF_UP);
        p.setStatus(PaymentStatus.SUCCESS);
        p.setChosenProcessor(pc.type());
        p.setFeeApplied(fee);
        p.setRequestedAt(Instant.now());
        p.setNetAmount(net);
    }

    private PaymentResponse toResponse(Payment p) {
        return new PaymentResponse(
                p.getId(),
                p.getChosenProcessor(),
                p.getAmount(),
                p.getFeeApplied(),
                p.getNetAmount(),
                p.getStatus().name());
    }
}
