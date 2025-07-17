package br.com.makersweb.rinhabackend2025.application.payment.create;

import br.com.makersweb.rinhabackend2025.domain.payment.Payment;
import br.com.makersweb.rinhabackend2025.domain.payment.PaymentGateway;

import java.util.Objects;

/**
 * @author anderson
 */
public class DefaultCreatePaymentUseCase extends CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public DefaultCreatePaymentUseCase(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public CreatePaymentOutput execute(final CreatePaymentCommand aCommand) {
        final var correlationId = aCommand.correlationId();
        final var amount = aCommand.amount();

        final var aPayment = Payment.newPayment(correlationId, amount);
        this.paymentGateway.create(aPayment);

        return CreatePaymentOutput.from("payment processed successfully");
    }

}
