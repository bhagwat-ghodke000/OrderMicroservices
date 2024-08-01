package com.microservices.ecommerce.repository;

import com.microservices.ecommerce.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
