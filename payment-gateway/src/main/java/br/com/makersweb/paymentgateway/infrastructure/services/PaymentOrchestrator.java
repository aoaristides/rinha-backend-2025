package br.com.makersweb.paymentgateway.infrastructure.services;

import br.com.makersweb.paymentgateway.domain.models.PaymentResponse;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public interface PaymentOrchestrator {

    PaymentResponse process(BigDecimal amount, String correlationId);

}
