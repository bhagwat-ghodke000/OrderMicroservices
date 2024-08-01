package com.microservices.product.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity<Integer> createCategory(@RequestBody Category category){

        Integer category1 = this.categoryService.createCategory(category);

        return new ResponseEntity<>(category1, HttpStatus.CREATED);
    }

}
