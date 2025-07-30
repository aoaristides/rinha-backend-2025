package br.com.makersweb.rinhabackend2025.application.payments.create;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 * @param correlationId
 * @param amount
 * @param requestedAt
 */
public record CreatePaymentCommand(
        String correlationId,
        BigDecimal amount,
        Instant requestedAt
) {

    public static CreatePaymentCommand with(
            final String correlationId,
            final BigDecimal amount,
            final Instant requestedAt
    ) {
        return new CreatePaymentCommand(correlationId, amount, requestedAt);
    }

}
