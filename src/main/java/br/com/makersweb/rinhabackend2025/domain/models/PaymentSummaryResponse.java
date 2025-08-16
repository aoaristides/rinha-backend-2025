package br.com.makersweb.rinhabackend2025.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aaristides
 * @param defaults
 * @param fallback
 */
public record PaymentSummaryResponse(
        @JsonProperty("default") PaymentSummary defaults,
        @JsonProperty("fallback") PaymentSummary fallback
) {
}
