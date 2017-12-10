package com.gederin.route;

import com.gederin.handler.PriceHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PriceRoute {

    private static final String ROOT = "/api";
    private static final String ALL_PRICES = "/prices";
    private static final String PRICE_BY_ID = "/price/{id}";
    private static final String SAVE_PRICE = "/price";

    @Bean
    RouterFunction<?> routes(PriceHandler handler) {
        return nest(path(ROOT),
                nest(accept(APPLICATION_JSON), route(GET(ALL_PRICES), handler::fetchAllPrices))
                        .andRoute(GET(PRICE_BY_ID), handler::fetchPriceById)
                        .andRoute(POST(SAVE_PRICE).and(contentType(APPLICATION_JSON)), handler::savePrice));
    }
}
