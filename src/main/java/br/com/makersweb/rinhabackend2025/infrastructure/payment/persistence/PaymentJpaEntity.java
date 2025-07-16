package br.com.makersweb.rinhabackend2025.infrastructure.payment.persistence;

import br.com.makersweb.rinhabackend2025.domain.payment.Payment;
import br.com.makersweb.rinhabackend2025.domain.payment.PaymentID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author anderson
 */
@Entity(name = "Payment")
@Table(name = "payments")
public class PaymentJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "correlationId", nullable = false)
    private String correlationId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "requested_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant requestedAt;

    public PaymentJpaEntity() {
    }

    private PaymentJpaEntity(String id, String correlationId, BigDecimal amount, Instant requestedAt) {
        this.id = id;
        this.correlationId = correlationId;
        this.amount = amount;
        this.requestedAt = requestedAt;
    }

    public static PaymentJpaEntity from(final Payment aPayment) {
        return new PaymentJpaEntity(
                aPayment.getId().getValue(),
                aPayment.getCorrelationId(),
                aPayment.getAmount(),
                aPayment.getRequestedAt()
        );
    }

    public Payment toAggregate() {
        return Payment.with(
                PaymentID.from(getId()),
                getCorrelationId(),
                getAmount(),
                getRequestedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }
}
