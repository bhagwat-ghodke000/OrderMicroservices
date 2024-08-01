package com.microservices.Order.repositoer;

import com.microservices.Order.orderLine.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {

    List<OrderLine> findAllByOrderId(Integer orderId);
}
