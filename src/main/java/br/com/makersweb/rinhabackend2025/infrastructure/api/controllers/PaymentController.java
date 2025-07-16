package br.com.makersweb.rinhabackend2025.infrastructure.api.controllers;

import br.com.makersweb.rinhabackend2025.application.payment.create.CreatePaymentCommand;
import br.com.makersweb.rinhabackend2025.application.payment.create.CreatePaymentUseCase;
import br.com.makersweb.rinhabackend2025.infrastructure.api.PaymentAPI;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.CreatePaymentProcessorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anderson
 */
@RestController
public class PaymentController implements PaymentAPI {

    private final CreatePaymentUseCase  createPaymentUseCase;

    public PaymentController(final CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @Override
    public ResponseEntity<?> createPayment(final CreatePaymentProcessorRequest input) {
        final var aCommand = CreatePaymentCommand.with(
                input.correlationId(),
                input.amount(),
                input.requestedAt()
        );

        return ResponseEntity.ok(this.createPaymentUseCase.execute(aCommand));
    }

}
