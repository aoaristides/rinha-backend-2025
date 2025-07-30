package br.com.makersweb.rinhabackend2025.domain.events;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author aaristides
 */
public interface DomainEvent extends Serializable {

    Instant occurredOn();

}
