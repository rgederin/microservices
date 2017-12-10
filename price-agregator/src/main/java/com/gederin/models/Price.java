package com.gederin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private String productId;
    private String minimal;
    private String avegare;
    private String maximum;
}
