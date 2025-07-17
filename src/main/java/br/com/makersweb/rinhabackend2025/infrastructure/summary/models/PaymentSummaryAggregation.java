package br.com.makersweb.rinhabackend2025.infrastructure.summary.models;

import java.math.BigDecimal;

/**
 * @author anderson
 * @param processedAtDefault
 * @param totalAmount
 * @param totalCount
 */
public record PaymentSummaryAggregation(
        Boolean processedAtDefault,
        BigDecimal totalAmount,
        Long totalCount
) {
}
