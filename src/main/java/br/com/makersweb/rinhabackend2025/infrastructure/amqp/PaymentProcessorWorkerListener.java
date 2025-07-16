package br.com.makersweb.rinhabackend2025.infrastructure.amqp;

import br.com.makersweb.rinhabackend2025.infrastructure.configuration.json.Json;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentProcessorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author anderson
 */
@Component
public class PaymentProcessorWorkerListener {

    private static final Logger log = LoggerFactory.getLogger(PaymentProcessorWorkerListener.class);

    static final String LISTENER_ID = "paymentProcessorWorkerListener";

    @RabbitListener(id = LISTENER_ID, queues = "${amqp.queues.payment-processor-created.queue}")
    public void onPaymentProcessorMessage(@Payload final String message) {
        log.info("Received message: {}", message);
        final var aResult = Json.readValue(message, PaymentProcessorMessage.class);
        log.info("Consumer message: {}", aResult.correlationId());
    }

}
