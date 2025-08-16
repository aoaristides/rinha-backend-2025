package br.com.makersweb.paymentgateway.infrastructure.configuration;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

/**
 * @author aaristides
 */
@Configuration
public class WebClientConfig {

    @Bean
    WebClient defaultWebClient(@Value("${timeouts.paymentMs}") long paymentMs) {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(paymentMs))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) paymentMs);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    WebClient healthWebClient(@Value("${timeouts.healthMs}") long healthMs) {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(healthMs))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) healthMs);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
