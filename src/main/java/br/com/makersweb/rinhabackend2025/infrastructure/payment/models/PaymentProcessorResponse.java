package br.com.makersweb.rinhabackend2025.infrastructure.payment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anderson
 * @param message
 */
public record PaymentProcessorResponse(
        @JsonProperty("message") String message
) {
}
