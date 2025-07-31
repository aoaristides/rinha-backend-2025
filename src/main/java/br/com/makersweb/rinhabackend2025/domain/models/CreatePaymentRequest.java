package br.com.makersweb.rinhabackend2025.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 *
 * @author aaristides
 * @param correlationId
 * @param amount
 */
public record CreatePaymentRequest(
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") BigDecimal amount
) {
}
