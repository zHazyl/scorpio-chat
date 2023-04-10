package com.tnh.cloudgateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {
    private final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    private final FilterUtils filterUtils;
    public ResponseFilter(FilterUtils filterUtils) {
        this.filterUtils = filterUtils;
    }

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestheaders = exchange.getRequest().getHeaders();
                // Grabs the correlation ID that was passed in to the original HTTP request
                String correlationId = filterUtils.getCorrelationId(requestheaders);
                logger.debug("Adding the correlation id to the outbound headers. {}", correlationId);
                exchange.getResponse().getHeaders().add(FilterUtils.CORRELATION_ID, correlationId);
                logger.debug("Completing outgoing request for {}", exchange.getRequest().getURI());
            }));
        };
    }
}
