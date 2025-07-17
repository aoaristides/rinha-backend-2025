package br.com.makersweb.rinhabackend2025.infrastructure.payment;

import br.com.makersweb.rinhabackend2025.domain.payment.Payment;
import br.com.makersweb.rinhabackend2025.domain.payment.PaymentGateway;
import br.com.makersweb.rinhabackend2025.infrastructure.configuration.annotations.PaymentProcessorCreatedQueue;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentRequest;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.persistence.PaymentJpaEntity;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.persistence.PaymentRepository;
import br.com.makersweb.rinhabackend2025.infrastructure.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author anderson
 */
@Component
public class DefaultPaymentGateway implements PaymentGateway {

    private static final Logger log = LoggerFactory.getLogger(DefaultPaymentGateway.class);

    private final EventService eventService;
    private final PaymentRepository paymentRepository;

    public DefaultPaymentGateway(
            @PaymentProcessorCreatedQueue final EventService eventService,
            final PaymentRepository paymentRepository
    ) {
        this.eventService = Objects.requireNonNull(eventService);
        this.paymentRepository = Objects.requireNonNull(paymentRepository);
    }

    @Override
    public Payment create(final Payment aPayment) {
        log.info("Creating Payment with id {}", aPayment.getId());
        final var paymentProcessorRequest = PaymentRequest.with(
                aPayment.getId().getValue(),
                aPayment.getCorrelationId(),
                aPayment.getAmount(),
                aPayment.getRequestedAt()
        );
        this.eventService.send(paymentProcessorRequest);
        log.info("Payment with id {} has been created", aPayment.getId());
        return aPayment;
    }

    @Override
    public Payment save(final Payment aPayment, final boolean byDefault) {
        log.info("Saving Payment with id {} and default {}", aPayment.getId(), byDefault);
        aPayment.addDefault(byDefault);
        return this.paymentRepository.save(PaymentJpaEntity.from(aPayment)).toAggregate();
    }
}
