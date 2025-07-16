package br.com.makersweb.rinhabackend2025.domain;

/**
 * @author anderson
 * @param <ID>
 */
public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    public AggregateRoot(final ID id) {
        super(id);
    }

}
