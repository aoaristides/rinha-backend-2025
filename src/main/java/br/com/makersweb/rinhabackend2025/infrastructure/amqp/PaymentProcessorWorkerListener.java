package br.com.makersweb.rinhabackend2025.infrastructure.amqp;

import br.com.makersweb.rinhabackend2025.domain.payment.Payment;
import br.com.makersweb.rinhabackend2025.domain.payment.PaymentGateway;
import br.com.makersweb.rinhabackend2025.infrastructure.configuration.DecisionMaker;
import br.com.makersweb.rinhabackend2025.infrastructure.configuration.json.Json;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentMessage;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentProcessorRequest;
import br.com.makersweb.rinhabackend2025.infrastructure.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author anderson
 */
@Component
public class PaymentProcessorWorkerListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessorWorkerListener.class);

    static final String LISTENER_ID = "paymentProcessorWorkerListener";

    private static final boolean PROCESSED_BY_DEFAULT = true;
    private static final boolean PROCESSED_BY_FALLBACK = false;

    private DecisionMaker.ServiceChoice SERVICE = DecisionMaker.ServiceChoice.DEFAULT;

    private final PaymentService paymentService;
    private final PaymentGateway paymentGateway;

    public PaymentProcessorWorkerListener(final PaymentService paymentService, PaymentGateway paymentGateway) {
        this.paymentService = Objects.requireNonNull(paymentService);
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Scheduled(fixedRate = 4998)
    private void processHealthCheck() {
        log.info("Init Processing health check");
        final var healthDefault = paymentService.healthDefault();
        log.info("Health Default is {}", healthDefault);
        final var healthFallback = paymentService.healthFallback();
        log.info("Health Fallback is {}", healthFallback);

        assert  healthDefault != null;
        assert healthFallback != null;

        SERVICE = DecisionMaker.makeDecision( healthDefault.failing(), healthDefault.minResponseTime(), healthFallback.failing(), healthFallback.minResponseTime() );
        log.info("SERVICE {}", SERVICE);
    }

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.payment-processor-created.queue}")
    public void onPaymentProcessorMessage(@Payload final String message) {
        log.info("Received message: {}", message);
        final var aResult = Json.readValue(message, PaymentMessage.class);
        final var aPaymentProcessor = PaymentProcessorRequest.with(
                aResult.correlationId(),
                aResult.amount()
        );

        if (SERVICE == DecisionMaker.ServiceChoice.BOTH_FAILING) {
            throw new IllegalStateException("BOTH FAILING");
        }

        if (SERVICE == DecisionMaker.ServiceChoice.DEFAULT) {
            final var payment = Payment.newPayment(aResult.correlationId(), aResult.amount());
            this.paymentService.processPaymentDefault(aPaymentProcessor);
            this.paymentGateway.save(payment, PROCESSED_BY_DEFAULT);
            log.info("Processed Payment Default is {}", aPaymentProcessor.correlationId());
        } else {
            final var payment = Payment.newPayment(aResult.correlationId(), aResult.amount());
            this.paymentService.processPaymentFallback(aPaymentProcessor);
            this.paymentGateway.save(payment, PROCESSED_BY_FALLBACK);
            log.info("Processed Payment Fallback is {}", aPaymentProcessor.correlationId());
        }
    }

}
