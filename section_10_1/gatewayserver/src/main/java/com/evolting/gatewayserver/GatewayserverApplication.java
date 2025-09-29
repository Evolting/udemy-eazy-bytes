package com.evolting.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

    @Bean
    public RouteLocator customEazyBankRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                    .path("/eazybank/accounts/**")
                    .filters(f -> f.rewritePath("/eazybank/accounts/(?<segment>.*)","/${segment}")
                            .circuitBreaker(config -> config.setName("accounts-circuit-breaker")
                                    .setFallbackUri("forward:/contact-support")))
                    .uri("lb://ACCOUNTS"))
                .route(p -> p
                    .path("/eazybank/loans/**")
                    .filters(f -> f.rewritePath("/eazybank/loans/(?<segment>.*)","/${segment}"))
                    .uri("lb://LOANS"))
                .route(p -> p
                    .path("/eazybank/cards/**")
                    .filters(f -> f.rewritePath("/eazybank/cards/(?<segment>.*)","/${segment}"))
                    .uri("lb://CARDS")).build();
    }
}
