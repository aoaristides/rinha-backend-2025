package br.com.makersweb.rinhabackend2025.infrastructure.payment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author anderson
 * @param correlationId
 * @param amount
 * @param requestedAt
 */
public record CreatePaymentProcessorRequest(
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") BigDecimal amount,
        @JsonProperty("requestedAt") String requestedAt
) {
}
