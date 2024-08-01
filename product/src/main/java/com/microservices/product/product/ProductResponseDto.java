package com.microservices.product.product;

import com.microservices.product.category.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private Integer id;

    private String name;

    private String description;

    private Double availableQuantity;

    private BigDecimal price;

    private double quantity;

    private Category category;
}
