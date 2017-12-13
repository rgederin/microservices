package com.gederin.handler;

import com.gederin.model.Price;
import com.gederin.repository.PriceRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@AllArgsConstructor
@Slf4j
public class PriceHandler {

    private final PriceRepository repository;

    public Mono<ServerResponse> fetchPriceById(ServerRequest serverRequest) {
        int personId = Integer.valueOf(serverRequest.pathVariable("id"));

        return repository.fetchPriceById(personId)
                .flatMap(this::buildFetchPriceByIdResponse)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> fetchAllPrices(ServerRequest serverRequest) {
        log.error("fetching all prices");
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(repository.fetchAllPrices(), Price.class);
    }

    public Mono<ServerResponse> savePrice(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .build(repository.savePrice(serverRequest.bodyToMono(Price.class)));
    }

    private Mono<ServerResponse> buildFetchPriceByIdResponse(Price price) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(fromObject(price));
    }
}
