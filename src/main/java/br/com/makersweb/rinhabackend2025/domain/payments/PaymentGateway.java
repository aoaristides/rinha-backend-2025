package br.com.makersweb.rinhabackend2025.domain.payments;

import java.util.Optional;

/**
 * @author aaristides
 */
public interface PaymentGateway {

    String create(Payment aPayment);

    Optional<Payment> findById(PaymentID anId);

}
