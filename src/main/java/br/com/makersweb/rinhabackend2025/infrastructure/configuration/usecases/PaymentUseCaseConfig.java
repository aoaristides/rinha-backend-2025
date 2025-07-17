package br.com.makersweb.rinhabackend2025.infrastructure.configuration.usecases;

import br.com.makersweb.rinhabackend2025.application.payment.create.CreatePaymentUseCase;
import br.com.makersweb.rinhabackend2025.application.payment.create.DefaultCreatePaymentUseCase;
import br.com.makersweb.rinhabackend2025.application.summary.retrieve.get.DefaultGetPaymentSummaryByRequestedAtUseCase;
import br.com.makersweb.rinhabackend2025.application.summary.retrieve.get.GetPaymentSummaryByRequestedAtUseCase;
import br.com.makersweb.rinhabackend2025.domain.payment.PaymentGateway;
import br.com.makersweb.rinhabackend2025.domain.summary.PaymentSummaryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anderson
 */
@Configuration
public class PaymentUseCaseConfig {

    private final PaymentGateway paymentGateway;
    private final PaymentSummaryGateway paymentSummaryGateway;

    public PaymentUseCaseConfig(PaymentGateway paymentGateway, PaymentSummaryGateway paymentSummaryGateway) {
        this.paymentGateway = paymentGateway;
        this.paymentSummaryGateway = paymentSummaryGateway;
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase() {
        return new DefaultCreatePaymentUseCase(this.paymentGateway);
    }

    @Bean
    public GetPaymentSummaryByRequestedAtUseCase getPaymentSummaryByRequestedAtUseCase() {
        return new DefaultGetPaymentSummaryByRequestedAtUseCase(this.paymentSummaryGateway);
    }

}
