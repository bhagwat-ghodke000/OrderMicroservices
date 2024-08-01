package com.microservices.Order.order;

import com.microservices.Order.orderLine.OrderLine;
import com.microservices.Order.product.PurchaseRequest;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Integer id,

        String reference,

        @Positive(message = "Order Amount Should be Positive")
        BigDecimal totalAmount,

        @NotNull(message = "Payment method should be present")
        PaymentMethod paymentMethod,

        @NotNull(message = "Customer should be present")
        @NotBlank(message = "Customer should be present")
        @NotEmpty(message = "Customer should be present")
        String customerId,

        @NotEmpty(message = "You should at least one purchase product")
        List<PurchaseRequest> products
) {
}
