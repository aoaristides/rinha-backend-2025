package br.com.makersweb.rinhabackend2025.infrastructure.services.impl;

import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.HealthResponse;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentProcessorRequest;
import br.com.makersweb.rinhabackend2025.infrastructure.payment.models.PaymentProcessorResponse;
import br.com.makersweb.rinhabackend2025.infrastructure.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

/**
 * @author anderson
 */
@Service
public class DefaultPaymentService implements PaymentService {

    private static final Logger log = LoggerFactory.getLogger(DefaultPaymentService.class);

    @Value("${api.default.url}")
    private String defaultApiUrl;

    @Value("${api.fallback.url}")
    private String fallbackApiUrl;

    private final RestTemplate restTemplate;

    public DefaultPaymentService(final @Autowired RestTemplate restTemplate) {
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
    }

    @Override
    public void processPaymentDefault(final PaymentProcessorRequest payload) {
        log.info("processPaymentDefault to message - {}", payload.correlationId());
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final var request = new HttpEntity<>(payload, headers);
        final var response = this.restTemplate.postForEntity(defaultApiUrl.concat("/payments"), request, PaymentProcessorResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && Objects.nonNull(response.getBody())) {
            log.info("ProcessPaymentDefault with success correlationId and requestedAt - [{},{}]", payload.correlationId(), response.getBody().message());
        }
    }

    @Override
    public void processPaymentFallback(final PaymentProcessorRequest payload) {
        log.info("processPaymentFallback to message - {}", payload.correlationId());
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final var request = new HttpEntity<>(payload, headers);
        final var response = this.restTemplate.postForEntity(fallbackApiUrl.concat("/payments"), request, PaymentProcessorResponse.class);
        if (response.getStatusCode().is2xxSuccessful() && Objects.nonNull(response.getBody())) {
            log.info("ProcessPaymentFallback with success correlationId and requestedAt - [{},{}]", payload.correlationId(), response.getBody().message());
        }
    }

    @Override
    public HealthResponse healthDefault() {
        log.info("Init method healthDefault");
        final var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final var response = this.restTemplate.getForEntity(defaultApiUrl.concat("/payments/service-health"), HealthResponse.class, headers);
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
    }

    @Override
    public HealthResponse healthFallback() {
        log.info("Init method healthFallback");
        final var headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final var response = this.restTemplate.getForEntity(fallbackApiUrl.concat("/payments/service-health"), HealthResponse.class, headers);
        return response.getStatusCode().is2xxSuccessful() ? response.getBody() : null;
    }
}
