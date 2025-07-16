package br.com.makersweb.rinhabackend2025.infrastructure.payment.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author anderson
 */
public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, String> {
}
