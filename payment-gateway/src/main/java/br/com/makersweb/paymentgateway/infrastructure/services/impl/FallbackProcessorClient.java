package br.com.makersweb.paymentgateway.infrastructure.services.impl;

import br.com.makersweb.paymentgateway.domain.enums.ProcessorType;
import br.com.makersweb.paymentgateway.domain.models.PaymentRequest;
import br.com.makersweb.paymentgateway.infrastructure.services.ProcessorClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author aaristides
 */
@Service
@Qualifier("fallbackClient")
@Slf4j
public class FallbackProcessorClient implements ProcessorClient {

    private final WebClient client;
    private final String baseUrl;
    private final BigDecimal fee;

    public FallbackProcessorClient(WebClient defaultWebClient,
                                   @Value("${processors.fallback.baseUrl}") String baseUrl,
                                   @Value("${processors.fallback.feePercent}") BigDecimal fee) {
        this.client = defaultWebClient;
        this.baseUrl = baseUrl;
        this.fee = fee;
    }

    @Override
    public ProcessorType type() {
        return ProcessorType.FALLBACK;
    }

    @Override
    public BigDecimal feePercent() {
        return fee;
    }

    @Override
    public Mono<ProcessorResult> pay(final String correlationId, final BigDecimal amount) {
        return client.post().uri(baseUrl.concat("/payments"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(PaymentRequest.with(correlationId, amount))
                .exchangeToMono(resp -> resp.bodyToMono(Map.class)
                        .defaultIfEmpty(Map.of())
                        .map(b -> new ProcessorResult(resp.statusCode().is2xxSuccessful(), resp.statusCode().value())));
    }
}
