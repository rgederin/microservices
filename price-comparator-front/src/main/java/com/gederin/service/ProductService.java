package com.gederin.service;

import com.gederin.dto.PriceDto;
import com.gederin.dto.ProductDto;
import com.gederin.model.ProductWithPrice;
import com.gederin.repository.PriceRepository;
import com.gederin.repository.ProductRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final PriceRepository priceRepository;

    public Flux<ProductWithPrice> getProductsWithPrice() throws InterruptedException {
        Flux<ProductDto> products = productRepository.getProductDtos();
        Flux<PriceDto> prices =  priceRepository.getPriceDtos();
        Flux<ProductWithPrice> productWithPrices = zipIntoProductWithPrices(products, prices);

        return productWithPrices;
    }

    private Flux<ProductWithPrice> zipIntoProductWithPrices(Flux<ProductDto> products, Flux<PriceDto> prices) {
        return Flux.zip(products, prices)
                .map(this::buildProductWithPrice);
    }

    private ProductWithPrice buildProductWithPrice(Tuple2<ProductDto, PriceDto> pair) {
        return ProductWithPrice.builder()
                .id(pair.getT1().getId())
                .name(pair.getT1().getName())
                .description(pair.getT1().getDescription())
                .category(pair.getT1().getCategory())
                .minimal(pair.getT2().getMinimal())
                .average(pair.getT2().getAvegare())
                .maximum(pair.getT2().getMaximum())
                .build();
    }
}
