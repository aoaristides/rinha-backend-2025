package br.com.makersweb.rinhabackend2025.application.payments.create;

import br.com.makersweb.rinhabackend2025.domain.payments.Payment;
import br.com.makersweb.rinhabackend2025.domain.payments.PaymentGateway;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultCreatePaymentUseCase extends CreatePaymentUseCase {

    private final PaymentGateway paymentGateway;

    public DefaultCreatePaymentUseCase(final PaymentGateway paymentGateway) {
        this.paymentGateway = Objects.requireNonNull(paymentGateway);
    }

    @Override
    public CreatePaymentOutput execute(final CreatePaymentCommand aCommand) {
        final var aCorrelationId = aCommand.correlationId();
        final var aAmount = aCommand.amount();
        final var aRequestedAt = aCommand.requestedAt();

        final var aPayment = Payment.newPayment(aCorrelationId, aAmount, aRequestedAt);
        return new CreatePaymentOutput(this.paymentGateway.create(aPayment));
    }

}
