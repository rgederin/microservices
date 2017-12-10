package com.gederin.service;

import com.gederin.model.Product;
import com.gederin.repository.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> fetchAllProducts() {
        return productRepository.fetchAllProducts();
    }

    public Mono<Product> fetchProductById(int id) {
        return productRepository.fetchProductById(id);
    }

    public Mono<Void> saveProduct(Mono<Product> product) {
        return productRepository.saveProduct(product);
    }
}
