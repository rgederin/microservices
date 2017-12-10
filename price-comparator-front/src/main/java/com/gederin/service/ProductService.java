package com.gederin.service;

import com.gederin.dto.PriceDto;
import com.gederin.dto.ProductDto;
import com.gederin.model.ProductWithPrice;
import com.gederin.repository.PriceRepository;
import com.gederin.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

import static com.gederin.dto.PriceDto.DEFAULT_PRICE_DTO;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final PriceRepository priceRepository;

    public Flux<ProductWithPrice> getProductsWithPrice() throws InterruptedException {
        Map<String, PriceDto> prices = getPrices();

        List<ProductDto> products = getProducts();

        return Flux.fromIterable(products.stream()
                .map(productDto -> {
                    PriceDto priceDto = prices.getOrDefault(productDto.getId(), DEFAULT_PRICE_DTO);
                    return buildProductWithPrice(productDto, priceDto);
                }).filter(productWithPrice -> Objects.nonNull(productWithPrice.getId()))
                .collect(Collectors.toList()));
    }

    private Map<String, PriceDto> getPrices() throws InterruptedException {
        return priceRepository.getPriceDtos()
                .collectList()
                .block()
                .stream()
                .collect(Collectors.toMap(PriceDto::getProductId, Function.identity()));
    }

    private List<ProductDto> getProducts() throws InterruptedException {
        return productRepository.getProductDtos()
                .collectList()
                .block();
    }

    private ProductWithPrice buildProductWithPrice(ProductDto productDto, PriceDto priceDto) {
        return ProductWithPrice.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .category(productDto.getCategory())
                .minimal(priceDto.getMinimal())
                .average(priceDto.getAvegare())
                .maximum(priceDto.getMaximum())
                .build();
    }
}
