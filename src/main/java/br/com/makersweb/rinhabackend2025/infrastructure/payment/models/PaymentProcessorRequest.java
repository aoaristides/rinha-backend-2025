package br.com.makersweb.rinhabackend2025.infrastructure.payment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author anderson
 * @param correlationId
 * @param amount
 */
public record PaymentProcessorRequest(
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") BigDecimal amount
) {

    public static PaymentProcessorRequest with(final String correlationId, final BigDecimal amount) {
        return new PaymentProcessorRequest(correlationId, amount);
    }

}
