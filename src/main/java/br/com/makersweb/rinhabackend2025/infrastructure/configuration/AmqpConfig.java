package br.com.makersweb.rinhabackend2025.infrastructure.configuration;

import br.com.makersweb.rinhabackend2025.infrastructure.configuration.annotations.PaymentProcessorCreatedQueue;
import br.com.makersweb.rinhabackend2025.infrastructure.configuration.annotations.PaymentProcessorEvents;
import br.com.makersweb.rinhabackend2025.infrastructure.configuration.properties.amqp.QueueProperties;
import org.springframework.amqp.core.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anderson
 */
@Configuration
public class AmqpConfig {

    @Bean
    @ConfigurationProperties("amqp.queues.payment-processor-created")
    @PaymentProcessorCreatedQueue
    QueueProperties paymentProcessorCreatedQueueProperties() {
        return new QueueProperties();
    }

    @Configuration
    static class Admin {

        @Bean
        @PaymentProcessorEvents
        Exchange paymentProcessorEventsExchange(@PaymentProcessorCreatedQueue QueueProperties props) {
            return new DirectExchange(props.getExchange());
        }

        @Bean
        @PaymentProcessorCreatedQueue
        Queue paymentProcessorCreatedQueue(@PaymentProcessorCreatedQueue QueueProperties props) {
            return new Queue(props.getQueue());
        }

        @Bean
        @PaymentProcessorCreatedQueue
        Binding paymentProcessorCreatedQueueBinding(
                @PaymentProcessorEvents DirectExchange exchange,
                @PaymentProcessorCreatedQueue Queue queue,
                @PaymentProcessorCreatedQueue QueueProperties props
        ) {
            return BindingBuilder.bind(queue).to(exchange).with(props.getRoutingKey());
        }
    }
}
