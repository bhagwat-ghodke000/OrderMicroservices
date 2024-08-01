package com.microservices.Order.service;

import com.microservices.Order.customer.CustomerClient;
import com.microservices.Order.customer.CustomerResponseDto;
import com.microservices.Order.exception.BusinessException;
import com.microservices.Order.kafka.OrderConfirmation;
import com.microservices.Order.kafka.OrderProcedure;
import com.microservices.Order.order.Order;
import com.microservices.Order.order.OrderRequest;
import com.microservices.Order.order.OrderResponse;
import com.microservices.Order.orderLine.OrderLineRequest;
import com.microservices.Order.product.ProductClient;
import com.microservices.Order.product.PurchaseRequest;
import com.microservices.Order.product.PurchaseResponseDto;
import com.microservices.Order.repositoer.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private  CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private OrderLineService orderLineService;

    @Autowired
    private OrderProcedure orderProcedure;

    public Integer CreateOrder(OrderRequest request) {

        CustomerResponseDto customerResponseDto = this.customerClient.findCustomerById(request.customerId()).orElseThrow(() -> new BusinessException("This is Customer is not found.."));

        List<PurchaseResponseDto> purchaseResponseDtos = this.productClient.purchaseProduct(request.products());

        Order order = this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {

            this.orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );

        }

        //to Start Payment Process...


        this.orderProcedure.sendOrderConfirmation(
                new OrderConfirmation(
                request.reference(),
                request.totalAmount(),
                request.paymentMethod(),
                customerResponseDto,
                purchaseResponseDtos

                )

        );

        return order.getId();

    }

    public List<OrderResponse> findAll(){

        List<Order> all = this.repository.findAll();

        List<OrderResponse> collect = all.stream().map(mapper::fromOrder).collect(Collectors.toList());

        return collect;
    }

    public OrderResponse getOrders(Integer orderId){

        Order order = this.repository.findById(orderId).orElseThrow(() -> new BusinessException("This is order is not present"));

        OrderResponse orderResponse = this.mapper.fromOrder(order);

        return orderResponse;
    }
}
