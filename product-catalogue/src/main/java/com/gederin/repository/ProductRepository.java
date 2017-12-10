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
        products.put(2, new Product("2", "Iphone 6S", "Gold", "Phone"));
        products.put(3, new Product("3", "Iphone 4", "Veteran", "Phone"));
    }

    public Flux<Product> fetchAllProducts() {
        return Flux.fromIterable(products.values());
    }

    public Mono<Product> fetchProductById(int id) {
        return Mono.justOrEmpty(products.get(id));
    }

    public Mono<Void> saveProduct(Mono<Product> product) {
        return product.doOnNext(this::save)
                .thenEmpty(Mono.empty());
    }

    private void save(Product product) {
        int id = products.size() + 1;
        products.put(id, product);
    }
}

