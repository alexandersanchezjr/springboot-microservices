package com.springboot.app.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
// import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    Logger logger = LoggerFactory.getLogger(GatewayGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Global pre filter executed");
        exchange.getRequest().mutate().headers(httpHeaders -> {
            httpHeaders.add("X-Request-Foo", "GlobalFilter");
        });
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("Global post filter executed");

            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("X-Request-Foo")).ifPresent(value -> {
                exchange.getResponse().getHeaders().add("X-Response-Foo", value);
                logger.info("X-Request-Foo: " + value);
            });

            exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "red").build());
            // exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
