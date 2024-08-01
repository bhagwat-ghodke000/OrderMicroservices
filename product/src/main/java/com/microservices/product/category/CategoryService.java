package com.microservices.product.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Integer createCategory(Category category){

        Category save = this.categoryRepo.save(category);
        return save.getId();
    }
}
