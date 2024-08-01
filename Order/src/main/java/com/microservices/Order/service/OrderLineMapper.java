package com.microservices.Order.service;

import com.microservices.Order.order.Order;
import com.microservices.Order.orderLine.OrderLine;
import com.microservices.Order.orderLine.OrderLineRequest;
import com.microservices.Order.orderLine.OrderLineResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {

        return OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {

        return new OrderLineResponse(orderLine.getId(),orderLine.getQuantity());
    }
}
