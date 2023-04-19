package com.tnh.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route(predicateSpec -> predicateSpec.path("/auth-service/**")
                                .filters(f -> f.rewritePath("/auth-service/(?<remaining>.*)", "/${remaining}").removeRequestHeader("Cookie,Set-Cookie"))
                                .uri("lb://AUTH")
                )
                .route(predicateSpec -> predicateSpec.path("/group-service/**")
                        .filters(f -> f.rewritePath("/group-service/(?<remaining>.*)", "/${remaining}").removeRequestHeader("Cookie,Set-Cookie"))
                        .uri("lb://GROUPCHAT")
                )
                .build();
    }
}
