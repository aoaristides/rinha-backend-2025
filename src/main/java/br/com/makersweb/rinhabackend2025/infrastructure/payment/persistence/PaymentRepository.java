package br.com.makersweb.rinhabackend2025.infrastructure.payment.persistence;

import br.com.makersweb.rinhabackend2025.infrastructure.summary.models.PaymentSummaryAggregation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author anderson
 */
public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, String> {

    @Query(value = "SELECT p.processed_at_default AS processedAtDefault, SUM(p.amount) AS totalAmount, COUNT(p.correlation_id) AS totalCount FROM payments AS p WHERE p.requested_at BETWEEN :from AND :to GROUP BY p.processed_at_default ORDER BY p.processed_at_default DESC", nativeQuery = true)
    List<PaymentSummaryAggregation> findSummariesPaymentsByRequestedAtBetween(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
