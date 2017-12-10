package com.gederin.repository;

import com.gederin.dto.ProductDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import reactor.core.publisher.Flux;

@Component
public class ProductRepository {

    @Value("${productCatalogueUri}")
    private String productCatalogServiceUrl;

    private final WebClient webClient;

    public ProductRepository(Builder webClientBuilder) {
        webClient = webClientBuilder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Flux<ProductDto> getProductDtos() throws InterruptedException {
        return webClient.get()
                .uri(productCatalogServiceUrl + "/products/")
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }
}
