package com.microservices.Order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${spring.application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{id}")
    Optional<CustomerResponseDto> findCustomerById(@PathVariable String id);

}
