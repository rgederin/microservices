package com.gederin.repository;

import com.gederin.dto.PriceDto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import reactor.core.publisher.Flux;

@Component
public class PriceRepository {

    @Value("${priceServiceUri}")
    private String priceServiceUrl;

    private final WebClient webClient;

    public PriceRepository(Builder webClientBuilder) {
        webClient = webClientBuilder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Flux<PriceDto> getPriceDtos() throws InterruptedException {
        return webClient.get()
                .uri(priceServiceUrl + "/prices/")
                .retrieve()
                .bodyToFlux(PriceDto.class);
    }
}
