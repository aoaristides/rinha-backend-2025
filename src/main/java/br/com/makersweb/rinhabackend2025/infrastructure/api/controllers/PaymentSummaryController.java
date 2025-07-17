package br.com.makersweb.rinhabackend2025.infrastructure.api.controllers;

import br.com.makersweb.rinhabackend2025.application.summary.retrieve.get.GetPaymentSummaryByRequestedAtUseCase;
import br.com.makersweb.rinhabackend2025.application.summary.retrieve.get.PaymentSummaryCommand;
import br.com.makersweb.rinhabackend2025.infrastructure.api.PaymentSummaryAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author anderson
 */
@RestController
public class PaymentSummaryController implements PaymentSummaryAPI {

    private static final Logger log = LoggerFactory.getLogger(PaymentSummaryController.class);

    private final GetPaymentSummaryByRequestedAtUseCase  getPaymentSummaryByRequestedAtUseCase;

    public PaymentSummaryController(final GetPaymentSummaryByRequestedAtUseCase getPaymentSummaryByRequestedAtUseCase) {
        this.getPaymentSummaryByRequestedAtUseCase = Objects.requireNonNull(getPaymentSummaryByRequestedAtUseCase);
    }

    @Override
    public ResponseEntity<?> getPaymentSummary(final LocalDateTime from, final LocalDateTime to) {
        log.debug("REST request to get payment summary");
        final var aCommand = PaymentSummaryCommand.with(from, to);
        return ResponseEntity.ok(this.getPaymentSummaryByRequestedAtUseCase.execute(aCommand));
    }
}
