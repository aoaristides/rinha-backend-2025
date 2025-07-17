package br.com.makersweb.rinhabackend2025.infrastructure.summary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anderson
 * @param byDefault
 * @param totalRequests
 * @param totalAmount
 */
public record Totals(
        @JsonIgnore Boolean byDefault,
        @JsonProperty("totalRequests") Integer totalRequests,
        @JsonProperty("totalAmount") Integer totalAmount
) {
}
