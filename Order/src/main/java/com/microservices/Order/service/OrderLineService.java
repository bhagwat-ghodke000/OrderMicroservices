package com.microservices.Order.service;

import com.microservices.Order.orderLine.OrderLine;
import com.microservices.Order.orderLine.OrderLineRequest;
import com.microservices.Order.orderLine.OrderLineResponse;
import com.microservices.Order.repositoer.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository repository;

    private OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest request) {

        OrderLine orderLine = mapper.toOrderLine(request);
        OrderLine save = this.repository.save(orderLine);

        return save.getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId){

        List<OrderLine> allByOrderId = this.repository.findAllByOrderId(orderId);

        List<OrderLineResponse> collect = allByOrderId.stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());

        return collect;

    }
}
