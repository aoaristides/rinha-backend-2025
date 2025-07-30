package br.com.makersweb.rinhabackend2025.domain.events;

/**
 * @author aaristides
 */
@FunctionalInterface
public interface DomainEventPublisher {

    void publishEvent(DomainEvent event);

}
