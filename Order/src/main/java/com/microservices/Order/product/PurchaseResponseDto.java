package com.microservices.Order.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponseDto {

    private Integer id;

    private String name;

    private String description;

    private Double availableQuantity;

    private BigDecimal price;

    private double quantity;
}