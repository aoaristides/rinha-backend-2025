package br.com.makersweb.rinhabackend2025.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 */
@Entity(name = "Payment")
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {

    @Id
    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "processed_by", nullable = false)
    private String processedBy;

    @Column(name = "requested_at")
    private Instant requestedAt;

}
