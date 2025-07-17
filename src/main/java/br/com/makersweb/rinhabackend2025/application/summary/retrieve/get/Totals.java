package br.com.makersweb.rinhabackend2025.application.summary.retrieve.get;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author anderson
 * @param byDefault
 * @param totalRequests
 * @param totalAmount
 */
public record Totals(
        @JsonIgnore Boolean byDefault,
        Integer totalRequests,
        Integer totalAmount
) {

    public static Totals with(final Boolean byDefault, final Integer totalRequests, final Integer totalAmount) {
        return new Totals(byDefault, totalRequests, totalAmount);
    }

}
