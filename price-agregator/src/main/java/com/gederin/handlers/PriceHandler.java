package com.gederin.handlers;


import com.gederin.models.Price;
import com.gederin.repositories.PriceRepository;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class PriceHandler {

    private final PriceRepository repository;

    public PriceHandler(PriceRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getPrice(ServerRequest request) {
        int personId = Integer.valueOf(request.pathVariable("id"));

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Price> personMono = repository.getPerson(personId);

        return personMono
                .flatMap(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createPrice(ServerRequest request) {
        Mono<Price> price = request.bodyToMono(Price.class);

        return ServerResponse.ok().build(repository.savePrice(price));
    }

    public Mono<ServerResponse> listPrice(ServerRequest request) {
        Flux<Price> people = repository.allPeople();

        return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Price.class);
    }
}
