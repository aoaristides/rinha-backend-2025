package br.com.makersweb.rinhabackend2025.application.payment.create;


import java.math.BigDecimal;

/**
 * @author anderson
 * @param correlationId
 * @param amount
 */
public record CreatePaymentCommand(
        String correlationId,
        BigDecimal amount
) {

    public static CreatePaymentCommand with(final String correlationId, final BigDecimal amount) {
        return new CreatePaymentCommand(correlationId, amount);
    }

}
