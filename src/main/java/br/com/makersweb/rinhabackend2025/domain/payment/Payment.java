package br.com.makersweb.rinhabackend2025.domain.payment;

import br.com.makersweb.rinhabackend2025.domain.AggregateRoot;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * @author anderson
 */
public class Payment extends AggregateRoot<PaymentID> {

   private String correlationId;
   private BigDecimal amount;
   private Instant requestedAt;

    private Payment(PaymentID anId, String correlationId, BigDecimal amount, Instant aRequestedAt) {
        super(anId);
        this.correlationId = correlationId;
        this.amount = amount;
        this.requestedAt =  Objects.requireNonNull(aRequestedAt, "'requestedAt' should not be null");
    }

    public static Payment newPayment(String correlationId, BigDecimal amount, String aRequestedAt) {
        final var anId = PaymentID.unique();
        final var requestedAt = convertDate(aRequestedAt);
        return new Payment(anId, correlationId, amount, requestedAt);
    }

    public static Payment with(
            final PaymentID anId,
            final String correlationId,
            final BigDecimal amount,
            final Instant aRequestedAt
    ) {
        return new Payment(anId, correlationId, amount, aRequestedAt);
    }

    public static Payment with(final Payment aPayment) {
        return with(
                aPayment.getId(),
                aPayment.correlationId,
                aPayment.amount,
                aPayment.requestedAt
        );
    }

    private static Instant convertDate(final String date) {
        try {
            //final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.000'Z'");
            return Instant.parse(date);
        } catch (DateTimeParseException e) {
            return Instant.now();
        }
    }

    @Override
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
