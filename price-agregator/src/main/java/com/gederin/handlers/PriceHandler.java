package com.gederin.handlers;

import com.gederin.models.Price;
import com.gederin.repositories.PriceRepository;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class PriceHandler {

    private final PriceRepository repository;

    public PriceHandler(PriceRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> fetchPriceById(ServerRequest serverRequest) {
        int personId = Integer.valueOf(serverRequest.pathVariable("id"));

        return repository.fetchPriceById(personId)
                .flatMap(this::buildFetchPriceByIdResponse)
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> fetchAllPrices(ServerRequest serverRequest) {
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
