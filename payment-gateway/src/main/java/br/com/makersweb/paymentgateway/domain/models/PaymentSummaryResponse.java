package br.com.makersweb.paymentgateway.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aaristides
 * @param defaults
 * @param fallback
 */
public record PaymentSummaryResponse(
        @JsonProperty("default") PSummary defaults,
        @JsonProperty("fallback") PSummary fallback
) {
}
