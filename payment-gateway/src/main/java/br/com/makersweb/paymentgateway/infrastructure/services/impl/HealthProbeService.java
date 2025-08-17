package br.com.makersweb.paymentgateway.infrastructure.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * @author aaristides
 */
@Service
@Slf4j
public class HealthProbeService {

    private final WebClient healthClient;
    private final String defaultUrl;
    private final String fallbackUrl;
    private volatile Health cachedDefault;
    private volatile Health cachedFallback;

    public HealthProbeService(
            WebClient healthWebClient,
            @Value("${processors.default.baseUrl}") String defaultBase,
            @Value("${processors.fallback.baseUrl}") String fallbackBase) {
        this.healthClient = healthWebClient;
        this.defaultUrl = defaultBase + "/payments/service-health";
        this.fallbackUrl = fallbackBase + "/payments/service-health";
    }

    private Health fetch(String url) {
        try {
            Map<String, Object> resp = healthClient.get().uri(url)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String,Object>>() {})
                    .block();
            boolean healthy = resp != null && Boolean.TRUE.equals(resp.get("healthy"));
            Number minMs = resp != null && resp.get("minProcessingMs") instanceof Number n ? n : 2000;
            return new Health(healthy, minMs.longValue(), Instant.now());
        } catch (Exception e) {
            return new Health(false, 10_000, Instant.now());
        }
    }

    private boolean expired(Health h) {
        return h == null || Duration.between(h.fetchedAt(), Instant.now()).toMillis() > 5000;
    }

    public Health defaultHealth() {
        if (expired(cachedDefault)) cachedDefault = fetch(defaultUrl);
        return cachedDefault;
    }

    public Health fallbackHealth() {
        if (expired(cachedFallback)) cachedFallback = fetch(fallbackUrl);
        return cachedFallback;
    }

    public record Health(boolean healthy, long minProcessingMs, Instant fetchedAt) {}

}
