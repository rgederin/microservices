package com.gederin.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductWithPrice {
    private String id;
    private String name;
    private String description;
    private String category;

    private String minimal;
    private String average;
    private String maximum;
}
