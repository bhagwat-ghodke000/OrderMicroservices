package com.microservices.product.product;

import com.microservices.product.category.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price,
        Category category
) {

}
