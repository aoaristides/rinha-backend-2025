package br.com.makersweb.rinhabackend2025.infrastructure.configuration;

import lombok.experimental.UtilityClass;

/**
 * @author anderson
 */
@UtilityClass
public class DecisionMaker {

    public static ServiceChoice makeDecision(boolean defaultWorking, int defaultTimeResponse, boolean fallbackWorking, int fallbackTimeResponse) {
        if (!defaultWorking) {
            if (!fallbackWorking) {
                if (defaultTimeResponse <= fallbackTimeResponse) {
                    return ServiceChoice.DEFAULT;
                } else {
                    return ServiceChoice.FALLBACK;
                }
            } else {
                return ServiceChoice.DEFAULT;
            }
        } else {
            if (!fallbackWorking) {
                return ServiceChoice.FALLBACK;
            } else {
                return ServiceChoice.BOTH_FAILING;
            }
        }
    }

    public enum ServiceChoice {
        DEFAULT,
        FALLBACK,
        BOTH_FAILING
    }

}
