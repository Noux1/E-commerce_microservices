package com.noux.productservice.response.category.mapper;

import com.noux.productservice.entity.Category;
import com.noux.productservice.response.category.DTO.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseMapper {
    public CategoryResponse responseMapping(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        return categoryResponse;
    }
}
