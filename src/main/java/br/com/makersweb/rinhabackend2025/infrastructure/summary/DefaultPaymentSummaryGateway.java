package br.com.makersweb.rinhabackend2025.infrastructure.summary;

import br.com.makersweb.rinhabackend2025.domain.summary.PaymentSummaryGateway;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.persistence.PaymentRepository;
import br.com.makersweb.rinhabackend2025.infrastructure.summary.models.PaymentSummaryAggregation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author anderson
 */
@Component
@Slf4j
public class DefaultPaymentSummaryGateway implements PaymentSummaryGateway {

    private final PaymentRepository paymentRepository;

    public DefaultPaymentSummaryGateway(final @Autowired PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<PaymentSummaryAggregation> findSummariesPaymentsByRequestedAtBetween(final LocalDateTime from, final LocalDateTime to) {
        log.debug("Init method findSummariesPaymentsByRequestedAtBetween.");
        return paymentRepository.findSummariesPaymentsByRequestedAtBetween(from, to);
    }
}
