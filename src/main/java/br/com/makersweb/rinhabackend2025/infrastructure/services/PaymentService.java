package br.com.makersweb.rinhabackend2025.infrastructure.services;

import br.com.makersweb.rinhabackend2025.domain.models.CreatePaymentRequest;
import br.com.makersweb.rinhabackend2025.domain.models.PaymentSummaryResponse;

import java.time.Instant;

/**
 * @author aaristides
 */
public interface PaymentService {

    void processPayment(final CreatePaymentRequest input);

    PaymentSummaryResponse paymentSummary(Instant from, Instant to);

    void purge();

}
