package br.com.makersweb.paymentgateway.infrastructure.services.impl;

import br.com.makersweb.paymentgateway.domain.Payment;
import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;
import br.com.makersweb.paymentgateway.domain.models.PSummary;
import br.com.makersweb.paymentgateway.domain.models.PaymentSummaryResponse;
import br.com.makersweb.paymentgateway.domain.repositories.PaymentRepository;
import br.com.makersweb.paymentgateway.infrastructure.services.PaymentSummary;
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
public class DefaultPaymentSummary implements PaymentSummary {

    private final PaymentRepository paymentRepository;

    public DefaultPaymentSummary(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
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

        Map<ProcessorType, List<Payment>> listMap = payments.stream().collect(Collectors.groupingBy(Payment::getChosenProcessor));
        return new PaymentSummaryResponse(
                summarize(listMap.getOrDefault(ProcessorType.DEFAULT, List.of())),
                summarize(listMap.getOrDefault(ProcessorType.FALLBACK, List.of()))
        );
    }

    @Override
    public void purge() {
        log.info("Init method purging payments");
        paymentRepository.deleteAll();
    }

    private PSummary summarize(final List<Payment> payments) {
        long total = payments.size();
        BigDecimal totalAmount = payments
                .stream().map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new PSummary(total, totalAmount);
    }
}
