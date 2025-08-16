package br.com.makersweb.paymentgateway.domain;

import br.com.makersweb.paymentgateway.domain.enums.PaymentStatus;
import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Payment")
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correlation_id", nullable = false, unique = true)
    private String correlationId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "chosen_processor")
    private ProcessorType chosenProcessor;

    @Column(name = "fee_applied")
    private BigDecimal feeApplied;

    @Column(name = "net_amount")
    private BigDecimal netAmount;

    @Column(name = "requested_at")
    private Instant requestedAt;

}
