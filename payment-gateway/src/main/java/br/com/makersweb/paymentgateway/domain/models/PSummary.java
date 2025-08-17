package br.com.makersweb.paymentgateway.domain.models;

import java.math.BigDecimal;

/**
 * @author aaristides
 * @param totalRequests
 * @param totalAmount
 */
public record PSummary(
        long totalRequests,
        BigDecimal totalAmount
) {
}
