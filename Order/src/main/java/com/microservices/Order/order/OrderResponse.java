package com.microservices.Order.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,

        String reference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        String customerId
) {
}
