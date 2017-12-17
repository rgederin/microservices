package com.gederin.repository;

import com.gederin.dto.PriceDto;

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
public class PriceRepository {

    @Value("${priceServiceUri}")
    private String priceServiceUrl;

    private final WebClient webClient;

    @Autowired
    public PriceRepository (WebClient webClient){
        this.webClient = webClient;
    }

    public Flux<PriceDto> getPriceDtos() throws InterruptedException {

        log.error("calling prices {}", priceServiceUrl);

        return webClient.get()
                .uri(priceServiceUrl)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToFlux(PriceDto.class);
    }
}
