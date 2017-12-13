package com.gederin.repository;

import com.gederin.dto.ProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class ProductRepository {

    @Value("${productCatalogueUri}")
    private String productCatalogServiceUrl;

    private final WebClient webClient;

    @Autowired
    public ProductRepository (WebClient webClient){
        this.webClient = webClient;
    }

    public Flux<ProductDto> getProductDtos() throws InterruptedException {
        log.error("calling products {}", productCatalogServiceUrl);

        return webClient
                .get()
                .uri(productCatalogServiceUrl + "/products/")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }
}
