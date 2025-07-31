package br.com.makersweb.rinhabackend2025.domain.repositories;

import br.com.makersweb.rinhabackend2025.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

/**
 * @author aaristides
 */
public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByRequestedAtBetween(Instant from, Instant to);

    List<Payment> findByRequestedAtAfter(Instant from);

    List<Payment> findByRequestedAtBefore(Instant to);

}
