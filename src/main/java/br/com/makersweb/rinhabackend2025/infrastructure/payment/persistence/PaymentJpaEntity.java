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

    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "requested_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant requestedAt;

    @Column(name = "processed_at_default", nullable = false)
    private Boolean processedAtDefault;

    public PaymentJpaEntity() {
    }

    private PaymentJpaEntity(String id, String correlationId, BigDecimal amount, Instant requestedAt,  Boolean processedAtDefault) {
        this.id = id;
        this.correlationId = correlationId;
        this.amount = amount;
        this.requestedAt = requestedAt;
        this.processedAtDefault = processedAtDefault;
    }

    public static PaymentJpaEntity from(final Payment aPayment) {
        return new PaymentJpaEntity(
                aPayment.getId().getValue(),
                aPayment.getCorrelationId(),
                aPayment.getAmount(),
                aPayment.getRequestedAt(),
                aPayment.getByDefault()
        );
    }

    public Payment toAggregate() {
        return Payment.with(
                PaymentID.from(getId()),
                getCorrelationId(),
                getAmount(),
                getRequestedAt(),
                getProcessedAtDefault()
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

    public Boolean getProcessedAtDefault() {
        return processedAtDefault;
    }

    public void setProcessedAtDefault(Boolean processedAtDefault) {
        this.processedAtDefault = processedAtDefault;
    }
}
