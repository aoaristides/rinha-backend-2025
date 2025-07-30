package br.com.makersweb.rinhabackend2025.infrastructure.payments;

import br.com.makersweb.rinhabackend2025.domain.payments.Payment;
import br.com.makersweb.rinhabackend2025.domain.payments.PaymentGateway;
import br.com.makersweb.rinhabackend2025.domain.payments.PaymentID;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author aaristides
 */
@Component
public class PaymentPostgresSQLGateway implements PaymentGateway {

    @Override
    public String create(Payment aPayment) {
        return "";
    }

    @Override
    public Optional<Payment> findById(PaymentID anId) {
        return Optional.empty();
    }
}
