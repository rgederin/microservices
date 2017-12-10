package com.gederin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {
    private String productId;
    private String minimal;
    private String avegare;
    private String maximum;

    public static final PriceDto DEFAULT_PRICE_DTO = new PriceDto("n/a", "n/a", "n/a", "n/a");
}
