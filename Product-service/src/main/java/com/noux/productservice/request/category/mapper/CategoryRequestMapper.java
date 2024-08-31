package com.noux.productservice.request.category.mapper;
import com.noux.productservice.entity.Category;
import com.noux.productservice.request.category.DTO.CategoryRequest;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequestMapper {
    public Category requestMapping(CategoryRequest request){
        return Category.builder()
                .id(request.getId())
                .Description(request.getDescription())
                .name(request.getName())
                .build();
    }
}
