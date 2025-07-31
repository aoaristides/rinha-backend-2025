package br.com.makersweb.rinhabackend2025.api.controllers;

import br.com.makersweb.rinhabackend2025.api.PaymentAPI;
import br.com.makersweb.rinhabackend2025.domain.models.CreatePaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaristides
 */
@RestController
@Slf4j
public class PaymentController implements PaymentAPI {

    @Override
    public ResponseEntity<?> createPayment(final CreatePaymentRequest input) {
        return null;
    }

    @Override
    public String health() {
        return "Status: OK";
    }
}
