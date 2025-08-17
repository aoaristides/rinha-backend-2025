package br.com.makersweb.paymentgateway.infrastructure.services;

import br.com.makersweb.paymentgateway.domain.models.PaymentSummaryResponse;

import java.time.Instant;

/**
 * @author aaristides
 */
public interface PaymentSummary {

    PaymentSummaryResponse paymentSummary(Instant from, Instant to);

    void purge();

}
