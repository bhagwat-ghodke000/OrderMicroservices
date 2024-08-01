package com.microservices.Order.controller;

import com.microservices.Order.orderLine.OrderLineResponse;
import com.microservices.Order.service.OrderLineService;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-line")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;

    @GetMapping("/order/{orderId}")
    ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable Integer orderId){

        List<OrderLineResponse> allByOrderId = this.orderLineService.findAllByOrderId(orderId);

        return new ResponseEntity<>(allByOrderId, HttpStatus.OK);

    }
}
