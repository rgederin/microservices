package com.gederin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private Integer productId;
    private Long minimal;
    private Long avegare;
    private Long maximum;
}
