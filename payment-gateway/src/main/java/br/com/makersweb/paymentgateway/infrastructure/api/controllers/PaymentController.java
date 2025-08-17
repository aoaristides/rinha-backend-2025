package br.com.makersweb.paymentgateway.infrastructure.api.controllers;

import br.com.makersweb.paymentgateway.domain.models.CreatePaymentRequest;
import br.com.makersweb.paymentgateway.infrastructure.api.PaymentAPI;
import br.com.makersweb.paymentgateway.infrastructure.services.PaymentOrchestrator;
import br.com.makersweb.paymentgateway.infrastructure.services.PaymentSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaristides
 */
@RestController
@Slf4j
@Validated
public class PaymentController implements PaymentAPI {

    private final PaymentOrchestrator paymentOrchestrator;
    private final PaymentSummary  paymentSummary;

    public PaymentController(PaymentOrchestrator paymentOrchestrator, PaymentSummary paymentSummary) {
        this.paymentOrchestrator = paymentOrchestrator;
        this.paymentSummary = paymentSummary;
    }

    @Override
    public ResponseEntity<?> createPayment(final CreatePaymentRequest input) {
        log.info("Init method createPayment to correlationId - {}", input.correlationId());
        var resp = paymentOrchestrator.process(input.amount(), input.correlationId());
        return ResponseEntity.status("SUCCESS".equals(resp.status()) ? 200 : 502).body(resp);
    }

    @Override
    public ResponseEntity<Void> purge() {
        log.info("Init method purging payments");
        paymentSummary.purge();
        return ResponseEntity.ok().build();
    }
}
