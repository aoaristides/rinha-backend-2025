package br.com.makersweb.paymentgateway.domain.models;

import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;

import java.math.BigDecimal;

/**
 * @author aaristides
 * @param paymentId
 * @param processor
 * @param amount
 * @param fee
 * @param net
 * @param status
 */
public record PaymentResponse(
        Long paymentId,
        ProcessorType processor,
        BigDecimal amount,
        BigDecimal fee,
        BigDecimal net,
        String status
) {
}
