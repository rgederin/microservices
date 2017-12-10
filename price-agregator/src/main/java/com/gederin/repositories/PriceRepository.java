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

        prices.put(1, new Price("1", "123", "200", "344"));
        prices.put(2, new Price("2", "13", "20", "34"));
    }

    public Flux<Price> fetchAllPrices() {
        return Flux.fromIterable(prices.values());
    }

    public Mono<Price> fetchPriceById(int id) {
        return Mono.justOrEmpty(prices.get(id));
    }

    public Mono<Void> savePrice(Mono<Price> personMono) {
        return personMono.doOnNext(this::save)
                .thenEmpty(Mono.empty());
    }

    private void save(Price price) {
        int id = prices.size() + 1;
        prices.put(id, price);

        log.info("Saved {} with id {}", price, id);
    }
}
