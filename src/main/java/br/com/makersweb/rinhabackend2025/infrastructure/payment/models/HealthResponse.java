package br.com.makersweb.rinhabackend2025.infrastructure.payment.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anderson
 * @param failing
 * @param minResponseTime
 */
public record HealthResponse(
        @JsonProperty("failing") Boolean failing,
        @JsonProperty("minResponseTime") Integer minResponseTime
) {
}
