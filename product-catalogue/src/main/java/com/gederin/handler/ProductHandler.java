package com.gederin.handler;

import com.gederin.model.Product;
import com.gederin.service.ProductService;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@AllArgsConstructor
public class ProductHandler {
    private final ProductService productService;

    public Mono<ServerResponse> fetchAllProducts(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(productService.fetchAllProducts(), Product.class);
    }

    public Mono<ServerResponse> fetchProductById(ServerRequest serverRequest) {
        int productId = Integer.valueOf(serverRequest.pathVariable("id"));

        return productService.fetchProductById(productId)
                .flatMap(this::fetchProductByIdResponse)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> saveProduct(ServerRequest request) {
        return ServerResponse.ok()
                .build(productService.saveProduct(request.bodyToMono(Product.class)));
    }

    private Mono<ServerResponse> fetchProductByIdResponse(Product product) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(fromObject(product));
    }
}
