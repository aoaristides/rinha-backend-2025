package br.com.makersweb.rinhabackend2025.domain;

import br.com.makersweb.rinhabackend2025.domain.events.DomainEvent;

import java.util.List;

/**
 * @author aaristides
 * @param <ID>
 */
public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

    protected AggregateRoot(final ID id, final List<DomainEvent> events) {
        super(id, events);
    }

}
