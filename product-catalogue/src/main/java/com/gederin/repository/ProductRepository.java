package com.gederin.repository;

import com.gederin.model.Product;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepository {
    private Map<Integer, Product> products;

    @PostConstruct
    public void initProductsList() {
        products = new LinkedHashMap<>();

        products.put(1, new Product("1", "Iphone X", "The newest one", "Phone"));
        products.put(1, new Product("2", "Iphone 6S", "Gold", "Phone"));
        products.put(1, new Product("3", "Iphone 4", "Veteran", "Phone"));
    }

    public Mono<Product> save(Mono<Product> product) {
        return Mono.just(products.put(Integer.valueOf(product.block().getId()), product.block()));
    }

    public Flux<Product> getAllProducts() {
        return Flux.fromIterable(products.values());
    }

    public Mono<Product> getProductById(int id) {
        return Mono.justOrEmpty(products.get(id));
    }
}

