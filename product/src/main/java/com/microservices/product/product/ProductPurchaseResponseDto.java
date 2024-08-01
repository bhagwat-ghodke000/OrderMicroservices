package com.microservices.product.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPurchaseResponseDto {

   private Integer id;
   private String name;
   private String description;
   private Double availableQuantity;
   private BigDecimal price;
}
