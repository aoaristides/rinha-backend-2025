package br.com.makersweb.paymentgateway.domain.repositories;

import br.com.makersweb.paymentgateway.domain.ProcessorAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author aaristides
 */
public interface ProcessorAttemptRepository extends JpaRepository<ProcessorAttempt, Long> {
}
