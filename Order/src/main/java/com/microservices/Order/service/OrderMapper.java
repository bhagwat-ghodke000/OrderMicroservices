package com.microservices.Order.service;

import com.microservices.Order.order.Order;
import com.microservices.Order.order.OrderRequest;
import com.microservices.Order.order.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request) {

        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .customerId(request.customerId())
                .totalAmount(request.totalAmount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
