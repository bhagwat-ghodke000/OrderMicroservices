package com.microservices.Order.repositoer;

import com.microservices.Order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
