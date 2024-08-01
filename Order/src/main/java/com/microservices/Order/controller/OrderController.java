package com.microservices.Order.controller;

import com.microservices.Order.order.OrderRequest;
import com.microservices.Order.order.OrderResponse;
import com.microservices.Order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
        Integer order = this.orderService.CreateOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAllOrders(){

        List<OrderResponse> all = this.orderService.findAll();

        return new ResponseEntity<>(all,HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> gertOrders(@PathVariable Integer orderId){

        OrderResponse orders = this.orderService.getOrders(orderId);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
