package org.example.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.time.Duration;
@Configuration
public class GatewayConfig {
    @Bean
    public GlobalFilter addDelayFilter() {
        return (exchange, chain) -> {
            // Add a delay for requests to "/delayed-service"
            if (exchange.getRequest().getPath().toString().contains("/delayed-service")) {
                return Mono.delay(Duration.ofSeconds(3)) // Add a 3-second delay
                        .then(chain.filter(exchange));
            }
            return chain.filter(exchange);
        };
    }
}
