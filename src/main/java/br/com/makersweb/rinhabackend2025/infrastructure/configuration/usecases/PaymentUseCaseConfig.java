package br.com.makersweb.rinhabackend2025.infrastructure.configuration.usecases;

import br.com.makersweb.rinhabackend2025.application.payment.create.CreatePaymentUseCase;
import br.com.makersweb.rinhabackend2025.application.payment.create.DefaultCreatePaymentUseCase;
import br.com.makersweb.rinhabackend2025.domain.payment.PaymentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anderson
 */
@Configuration
public class PaymentUseCaseConfig {

    private final PaymentGateway paymentGateway;

    public PaymentUseCaseConfig(final PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase() {
        return new DefaultCreatePaymentUseCase(this.paymentGateway);
    }

}
