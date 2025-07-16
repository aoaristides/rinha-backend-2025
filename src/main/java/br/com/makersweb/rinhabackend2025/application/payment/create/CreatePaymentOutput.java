package br.com.makersweb.rinhabackend2025.application.payment.create;

/**
 * @author anderson
 * @param message
 */
public record CreatePaymentOutput(
        String message
) {

    public static CreatePaymentOutput from(final String aMessage) {
        return new CreatePaymentOutput(aMessage);
    }

}
