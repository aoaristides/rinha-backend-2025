package br.com.makersweb.rinhabackend2025.application.payments.create;

/**
 * @author aaristides
 * @param message
 */
public record CreatePaymentOutput(
        String message
) {

    public static CreatePaymentOutput from(final String message) {
        return new CreatePaymentOutput(message);
    }

}
