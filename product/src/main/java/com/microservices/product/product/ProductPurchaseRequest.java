package com.microservices.product.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Product Id is required")
        Integer productId,
        @NotNull(message = "Product Quantity is Required")
        double quantity
) {
}
