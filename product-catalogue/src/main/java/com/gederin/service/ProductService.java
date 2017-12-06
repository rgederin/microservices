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
        return productRepository.getAllProducts();
    }

    public Mono<Product> saveProduct(Mono<Product> product) {
        return productRepository.save(product);
    }

    public Mono<Product> fetchProductById(int id){
        return productRepository.getProductById(id);
    }
}
