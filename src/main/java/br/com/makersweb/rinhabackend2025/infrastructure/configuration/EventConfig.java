package br.com.makersweb.rinhabackend2025.infrastructure.configuration;

import br.com.makersweb.rinhabackend2025.infrastructure.configuration.annotations.PaymentProcessorCreatedQueue;
import br.com.makersweb.rinhabackend2025.infrastructure.configuration.properties.amqp.QueueProperties;
import br.com.makersweb.rinhabackend2025.infrastructure.services.EventService;
import br.com.makersweb.rinhabackend2025.infrastructure.services.impl.RabbitEventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anderson
 */
@Configuration
public class EventConfig {

    @Bean
    @PaymentProcessorCreatedQueue
    @ConditionalOnMissingBean
    EventService paymentProcessorCreatedEventService(
            @PaymentProcessorCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }

}
