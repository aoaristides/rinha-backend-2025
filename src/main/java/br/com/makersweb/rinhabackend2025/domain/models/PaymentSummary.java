package br.com.makersweb.rinhabackend2025.domain.models;

import java.math.BigDecimal;

/**
 * @author aaristides
 * @param totalRequests
 * @param totalAmount
 */
public record PaymentSummary(
        long totalRequests,
        BigDecimal totalAmount
) {
}
