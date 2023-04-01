package com.example.spring.gateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
public class RoutesConfiguration {

    @Value("${uri.api.clients}")
    private String uriApiClients;

    @Bean
    public KeyResolver userKeyResolver(){
        return exchange -> Mono.just(Objects.requireNonNull(
                exchange.getRequest()
                        .getRemoteAddress()
                        .getAddress()
                        .getHostAddress()));
    }

    /*
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("clients",
                        p -> p.path("/clients")
                                .filters(f -> f
                                        .addRequestHeader("X-Request-Client", "gateway")
                                        .addResponseHeader("X-Response-Client", "gateway")
                                        .rewritePath("/clients", "/v1/api/clients")
                                )
                        .uri(uriApiClients))
                .route("clientSearch",
                        p -> p.path("/clients/search")
                                .and()
                                .method("GET")
                                .and()
                                .header("X-Subscription-Id", "Curso")
                                .filters(f -> f
                                        .addRequestHeader("X-Request-Client", "gateway")
                                        .addResponseHeader("X-Response-Client", "gateway")
                                        .rewritePath("/clients/search(?<segment>.*)", "/v1/api/clients/search${segment}")
                                )
                                .uri(uriApiClients))
                .build();
    }
    */
}
