package br.com.makersweb.paymentgateway.infrastructure.services;

import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public interface ProcessorClient {

    ProcessorType type();

    BigDecimal feePercent();

    Mono<ProcessorResult> pay(String correlationId, BigDecimal amount);

    record ProcessorResult(boolean success, int httpStatus) {}

}
