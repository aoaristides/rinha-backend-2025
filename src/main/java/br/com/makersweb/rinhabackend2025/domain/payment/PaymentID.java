package br.com.makersweb.rinhabackend2025.domain.payment;

import br.com.makersweb.rinhabackend2025.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author anderson
 */
public class PaymentID extends Identifier {

    private final String value;

    private PaymentID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PaymentID unique() {
        return PaymentID.from(UUID.randomUUID().toString());
    }

    public static PaymentID from(final String anId) {
        return new PaymentID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PaymentID that = (PaymentID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
