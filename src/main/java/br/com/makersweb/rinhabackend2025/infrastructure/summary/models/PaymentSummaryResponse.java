package br.com.makersweb.rinhabackend2025.infrastructure.summary.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anderson
 * @param defaults
 * @param fallback
 */
public record PaymentSummaryResponse(
    @JsonProperty("default") Totals defaults,
    @JsonProperty("fallback") Totals fallback
) {
}
