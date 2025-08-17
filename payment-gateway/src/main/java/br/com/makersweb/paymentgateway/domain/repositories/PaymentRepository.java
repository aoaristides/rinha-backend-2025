package br.com.makersweb.paymentgateway.domain.repositories;

import br.com.makersweb.paymentgateway.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByCorrelationId(final String correlationId);

    List<Payment> findByRequestedAtBetween(Instant from, Instant to);

    List<Payment> findByRequestedAtAfter(Instant from);

    List<Payment> findByRequestedAtBefore(Instant to);

}
