package br.com.makersweb.rinhabackend2025.domain.payments;

import br.com.makersweb.rinhabackend2025.domain.AggregateRoot;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 */
public class Payment extends AggregateRoot<PaymentID> {

    private String correlationId;
    private BigDecimal amount;
    private Instant requestedAt;

    private Payment(
            final PaymentID anId,
            final String correlationId,
            final BigDecimal amount,
            final Instant aRequestedAt
    ) {
        super(anId);
        this.correlationId = correlationId;
        this.amount = amount;
        this.requestedAt = aRequestedAt;
    }

    public static Payment newPayment(final String correlationId, final BigDecimal amount, final Instant requestedAt) {
        final var id = PaymentID.unique();
//        final var requestedAt = Instant.now();
        return new Payment(id, correlationId, amount, requestedAt);
    }

    public static Payment with(final PaymentID anId, final String correlationId, final BigDecimal amount,  final Instant requestedAt) {
        return new Payment(anId, correlationId, amount, requestedAt);
    }

    public static Payment with(final Payment aPayment) {
        return with(
                aPayment.getId(),
                aPayment.correlationId,
                aPayment.amount,
                aPayment.requestedAt
        );
    }

    public PaymentID getId() {
        return id;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }
}
