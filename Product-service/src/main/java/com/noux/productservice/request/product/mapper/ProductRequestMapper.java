package com.noux.productservice.request.product.mapper;

import com.noux.productservice.entity.Category;
import com.noux.productservice.entity.Product;
import com.noux.productservice.exception.CategoryNotFoundException;
import com.noux.productservice.repository.CategoryRepository;
import com.noux.productservice.request.product.DTO.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper {
    private final CategoryRepository categoryRepository;

    public ProductRequestMapper(CategoryRepository categoryRepository){
        this.categoryRepository= categoryRepository;
    }
    public Product requestMapping(ProductRequest request){
        Category category = categoryRepository.findById(request.getCategoryId())
                 .orElseThrow(() -> new CategoryNotFoundException(
                         "Category not found "
        ));

         return Product.builder()
                .name(request.getName())
                .Description(request.getDescription())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .category(category)
                 .build();

    }
}
