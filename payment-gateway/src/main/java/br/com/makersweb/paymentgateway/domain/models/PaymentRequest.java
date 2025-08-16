package br.com.makersweb.paymentgateway.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author aaristides
 * @param correlationId
 * @param amount
 */
public record PaymentRequest(
        @JsonProperty("correlationId") String correlationId,
        @JsonProperty("amount") BigDecimal amount
) {

    public static PaymentRequest with(final String correlationId, final BigDecimal amount) {
        return new PaymentRequest(correlationId, amount);
    }

}
