package br.com.makersweb.paymentgateway.domain;

import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author aaristides
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProcessorAttempt")
@Table(name = "processor_attempts")
public class ProcessorAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "payment_id")
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    private ProcessorType processor;

    private boolean success;

    @Column(name = "http_status")
    private Integer httpStatus;

    @Column(name = "latency_ms")
    private Long latencyMs;

    private String error;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

}
