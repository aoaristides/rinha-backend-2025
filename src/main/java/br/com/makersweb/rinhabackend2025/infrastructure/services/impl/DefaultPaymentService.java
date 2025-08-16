package br.com.makersweb.rinhabackend2025.infrastructure.services.impl;

import br.com.makersweb.rinhabackend2025.domain.entity.Payment;
import br.com.makersweb.rinhabackend2025.domain.models.CreatePaymentRequest;
import br.com.makersweb.rinhabackend2025.domain.models.PaymentProcessorStatus;
import br.com.makersweb.rinhabackend2025.domain.models.PaymentSummary;
import br.com.makersweb.rinhabackend2025.domain.models.PaymentSummaryResponse;
import br.com.makersweb.rinhabackend2025.domain.repositories.PaymentRepository;
import br.com.makersweb.rinhabackend2025.infrastructure.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author aaristides
 */
@Service
@Slf4j
@AllArgsConstructor
public class DefaultPaymentService implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ServiceHealthManager serviceHealthManager;

    @Override
    public void processPayment(final CreatePaymentRequest input) {
        log.info("Init method processing payment to correlationId: {}", input.correlationId());

        PaymentProcessorStatus defaultStatus = serviceHealthManager.getStatus("default");
        PaymentProcessorStatus fallbackStatus = serviceHealthManager.getStatus("fallback");

        if (defaultStatus == PaymentProcessorStatus.DOWN && fallbackStatus == PaymentProcessorStatus.DOWN) {
            log.info("Default and Fallback are DOWN");
            return;
        }

        
    }

    @Override
    public PaymentSummaryResponse paymentSummary(final Instant from, final Instant to) {
        log.info("Init method payment summary into from {} to {} ", from, to);
        List<Payment> payments;

        if (from != null && to != null) {
            payments = paymentRepository.findByRequestedAtBetween(from, to);
        } else if (from != null) {
            payments = paymentRepository.findByRequestedAtAfter(from);
        } else if (to != null) {
            payments = paymentRepository.findByRequestedAtBefore(to);
        } else {
            payments = paymentRepository.findAll();
        }

        Map<String, List<Payment>> listMap = payments.stream().collect(Collectors.groupingBy(Payment::getProcessedBy));
        return new PaymentSummaryResponse(
                summarize(listMap.getOrDefault("default", List.of())),
                summarize(listMap.getOrDefault("fallback", List.of()))
        );
    }

    @Override
    public void purge() {
        log.info("Init method purging payments");
        paymentRepository.deleteAll();
    }

    private void save(final Payment payment) {
        log.info("Init method saving payment into correlationId - {}", payment.getCorrelationId());
        paymentRepository.save(payment);
    }

    private PaymentSummary summarize(final List<Payment> payments) {
        long total = payments.size();
        BigDecimal totalAmount = payments
                .stream().map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new PaymentSummary(total, totalAmount);
    }
}
