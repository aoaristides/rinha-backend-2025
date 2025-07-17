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
public record PaymentMessage(
        @JsonProperty("id") String id,
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("requestedAt") Instant requestedAt
) {
}
