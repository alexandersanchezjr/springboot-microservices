package com.springboot.app.gateway.filter.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class RequestGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestGatewayFilterFactory.Config> {

    public RequestGatewayFilterFactory() {
        super(Config.class);
    }

    private Logger logger = LoggerFactory.getLogger(RequestGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter((exchange, chain) -> {
            logger.info("Request pre filter executed: " + config.message);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                Optional.ofNullable(config.value).ifPresent(value -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.name, value).build());
                    logger.info("{}: {}", config.name, value);
                });

                logger.info("Request post filter executed: " + config.message);
            }));
        }, 2);
    }

    public static class Config {

        private String message;
        private String name;
        private String value;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
