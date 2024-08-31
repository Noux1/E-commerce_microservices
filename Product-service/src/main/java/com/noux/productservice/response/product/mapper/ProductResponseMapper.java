package com.noux.productservice.response.product.mapper;
import com.noux.productservice.entity.Product;
import com.noux.productservice.response.product.DTO.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper {
    public ProductResponse responseMapping(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setAvailableQuantity(product.getAvailableQuantity());
        productResponse.setPrice(product.getPrice());
        productResponse.setCategoryId(product.getCategory().getId());
        return productResponse;
    }
}
