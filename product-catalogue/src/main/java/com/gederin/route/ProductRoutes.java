package com.gederin.route;

import com.gederin.handler.ProductHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRoutes {

    private static final String ROOT = "/api";
    private static final String ALL_PRODUCTS = "/products";
    private static final String PRODUCT_BY_ID = "/product/{id}";
    private static final String SAVE_PRODUCT = "/product";

    @Bean
    RouterFunction<?> routes(ProductHandler handler) {
        return nest(path(ROOT),
                nest(accept(APPLICATION_JSON), route(GET(ALL_PRODUCTS), handler::fetchAllProducts)
                        .andRoute(GET(PRODUCT_BY_ID), handler::fetchProductById))
                        .andRoute(POST(SAVE_PRODUCT), handler::saveProduct));
    }
}
