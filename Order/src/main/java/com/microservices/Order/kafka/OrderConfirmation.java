package com.microservices.Order.kafka;

import com.microservices.Order.customer.CustomerResponseDto;
import com.microservices.Order.order.PaymentMethod;
import com.microservices.Order.product.PurchaseResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,

        BigDecimal totalAmount,

        PaymentMethod paymentMethod,

        CustomerResponseDto customer,

        List<PurchaseResponseDto> products
) {
}
