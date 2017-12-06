package com.gederin.route;

import com.gederin.model.Product;
import com.gederin.service.ProductService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ProductRoutes {

    @Bean
    RouterFunction<?> routes(ProductService productService) {
        return nest(path("/api"),
                route(GET("/product/{id}"), serverRequest -> ok()
                        .contentType(APPLICATION_JSON)
                        .body(productService.fetchProductById(Integer.parseInt(serverRequest.pathVariable("id"))), Product.class))

                        .andRoute(GET("/products"), serverRequest -> ok()
                                .contentType(APPLICATION_JSON)
                                .body(productService.fetchAllProducts(), Product.class))

                        .andRoute(POST("/product"), serverRequest -> {
                            productService.saveProduct(serverRequest.bodyToMono(Product.class)).subscribe();
                            return ok().contentType(APPLICATION_JSON).build();
                        }));
    }
}


