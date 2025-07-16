package br.com.makersweb.rinhabackend2025.infrastructure.payment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author anderson
 * @param id
 * @param correlationId
 * @param amount
 * @param requestedAt
 */
public record PaymentProcessorRequest(
        @JsonProperty("id") String id,
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("requestedAt") Instant requestedAt
) {

    public static PaymentProcessorRequest with(final String id, final String correlationId, final BigDecimal amount, final Instant requestedAt) {
        return new PaymentProcessorRequest(id, correlationId, amount, requestedAt);
    }

}
