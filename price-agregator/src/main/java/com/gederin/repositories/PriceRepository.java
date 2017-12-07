package com.gederin.repositories;


import com.gederin.models.Price;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class PriceRepository {
    private final Map<Integer, Price> prices;

    public PriceRepository() {
        prices = new HashMap<>();

        prices.put(1, new Price(1, 123L, 200L, 344L));
    }

    public Flux<Price> allPeople() {
        return Flux.fromIterable(prices.values());
    }

    public Mono<Price> getPerson(int id) {
        return Mono.justOrEmpty(prices.get(id));
    }

    public Mono<Void> savePrice(Mono<Price> personMono) {
        return personMono.doOnNext(price -> {
            int id = prices.size() + 1;
            prices.put(id, price);

            log.info("Saved {} with id {}", price, id);
        }).thenEmpty(Mono.empty());
    }
}
