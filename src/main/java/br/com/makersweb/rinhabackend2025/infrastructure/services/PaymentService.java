package br.com.makersweb.rinhabackend2025.infrastructure.services;


import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.HealthResponse;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentProcessorRequest;

/**
 * @author anderson
 */
public interface PaymentService {

    void processPaymentDefault(final PaymentProcessorRequest request);

    void processPaymentFallback(final PaymentProcessorRequest request);

    HealthResponse healthDefault();

    HealthResponse healthFallback();

}
