package com.product.iphone.worker;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

public class ProductDTO {
    @NotNull
    @Size(min=1, max=255)
    private String name;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @PositiveOrZero
    private Integer quantity;
}
