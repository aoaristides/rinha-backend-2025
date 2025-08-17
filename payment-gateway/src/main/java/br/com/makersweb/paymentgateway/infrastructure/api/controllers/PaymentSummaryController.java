package br.com.makersweb.paymentgateway.infrastructure.api.controllers;

import br.com.makersweb.paymentgateway.infrastructure.api.PaymentSummaryAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * @author aaristides
 */
@RestController
@Slf4j
public class PaymentSummaryController implements PaymentSummaryAPI {

    @Override
    public ResponseEntity<?> getPaymentSummary(final Instant from, final Instant to) {
        return null;
    }
}
