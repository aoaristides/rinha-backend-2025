package br.com.makersweb.rinhabackend2025.infrastructure.services.impl;

import br.com.makersweb.rinhabackend2025.domain.models.PaymentProcessorStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author aaristides
 */
@Component
public class ServiceHealthManager {

    private final ConcurrentHashMap<String, PaymentProcessorStatus> healthMap = new ConcurrentHashMap<>();

    public void setStatus(String serviceName, PaymentProcessorStatus status) {
        healthMap.put(serviceName, status);
    }

    public PaymentProcessorStatus getStatus(String serviceName) {
        return healthMap.getOrDefault(serviceName, PaymentProcessorStatus.UP);
    }

    public boolean isUp(String serviceName) {
        return getStatus(serviceName) == PaymentProcessorStatus.UP;
    }
}
