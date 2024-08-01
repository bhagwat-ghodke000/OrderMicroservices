package com.microservices.product.product;

import com.microservices.product.category.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        @NotNull(message = "The Product Name is Required")
        String name,

        @NotNull(message = "The Description is required")
        String description,

        @Positive(message = "Available quantity should be positive")
        Double availableQuantity,

        @Positive(message = "Price should be positive")
        BigDecimal price,

        @NotNull(message = "The categoryId is required")
        Integer categoryId
) {
}
