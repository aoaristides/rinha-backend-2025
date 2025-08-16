package br.com.makersweb.paymentgateway.infrastructure.configuration;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author aaristides
 */
@Configuration
public class ResilienceConfig {

    @Bean
    public Bulkhead defaultBulkhead(BulkheadRegistry reg) {
        return reg.bulkhead("defaultProcessor");
    }

    @Bean
    public Bulkhead fallbackBulkhead(BulkheadRegistry reg) {
        return reg.bulkhead("fallbackProcessor");
    }

    @Bean
    public CircuitBreaker cbDefault(CircuitBreakerRegistry reg) {
        return reg.circuitBreaker("defaultProcessor");
    }

    @Bean
    public CircuitBreaker cbFallback(CircuitBreakerRegistry reg) {
        return reg.circuitBreaker("fallbackProcessor");
    }

}
