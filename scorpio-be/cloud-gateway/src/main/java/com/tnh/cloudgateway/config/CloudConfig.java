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
                                .filters(f -> f.rewritePath("/auth-service/(?<remaining>.*)", "/api/v1/${remaining}").removeRequestHeader("Cookie,Set-Cookie"))
                                .uri("lb://AUTH")
                )
                .route(predicateSpec -> predicateSpec.path("/group-service/**")
                        .filters(f -> f.rewritePath("/group-service/(?<remaining>.*)", "/api/v1/${remaining}").removeRequestHeader("Cookie,Set-Cookie"))
                        .uri("lb://GROUPCHAT")
                )
                .route(predicateSpec -> predicateSpec.path("/friend-service/**")
                                .filters(f -> f.rewritePath("/friend-service/(?<remaining>.*)", "/api/v1/${remaining}"))
                                .uri("lb://FRIENDCHAT")
                )
                .route(predicateSpec -> predicateSpec.path("/chat-messages-service/**")
                                .filters(f -> f.rewritePath("/chat-messages-service/(?<remaining>.*)", "/${remaining}"))
                                .uri("lb://CHAT-MESSAGES")
                )
                //add removeRequestHeader= Cookie, Set-cookie to propagate authorization HTTP heaader
                .route(predicateSpec -> predicateSpec.path("/messages-websocket-service/**")
                                .filters(f -> f.rewritePath("/messages-websocket-service/(?<remaining>.*)", "/${remaining}").removeRequestHeader("Cookie,Set-Cookie"))
                                .uri("lb://MESSAGES-WEBSOCKET")
                )
                .build();
    }
}
