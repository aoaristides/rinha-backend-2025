package br.com.makersweb.paymentgateway.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreatePaymentRequest(
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") @NotNull @Positive BigDecimal amount
) {
}
