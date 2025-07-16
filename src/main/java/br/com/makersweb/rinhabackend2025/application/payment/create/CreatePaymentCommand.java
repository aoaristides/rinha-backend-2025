package br.com.makersweb.rinhabackend2025.application.payment.create;


import java.math.BigDecimal;

/**
 * @author anderson
 * @param correlationId
 * @param amount
 * @param requestedAt
 */
public record CreatePaymentCommand(
        String correlationId,
        BigDecimal amount,
        String requestedAt
) {

    public static CreatePaymentCommand with(final String correlationId, final BigDecimal amount, final String requestedAt) {
        return new CreatePaymentCommand(correlationId, amount, requestedAt);
    }

}
