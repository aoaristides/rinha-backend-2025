package br.com.makersweb.paymentgateway.domain.repositories;

import br.com.makersweb.paymentgateway.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aaristides
 */
public interface PaymentRepository extends JpaRepository<Payment, String> {

}
