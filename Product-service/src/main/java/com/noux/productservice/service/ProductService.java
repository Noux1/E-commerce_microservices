package com.noux.productservice.service;

import com.noux.productservice.entity.Product;
import com.noux.productservice.exception.CategoryNotFoundException;
import com.noux.productservice.exception.ProductNotFoundException;
import com.noux.productservice.exception.ProductPurchaseException;
import com.noux.productservice.repository.CategoryRepository;
import com.noux.productservice.repository.ProductRepository;
import com.noux.productservice.request.category.DTO.CategoryRequest;
import com.noux.productservice.request.category.mapper.CategoryRequestMapper;
import com.noux.productservice.request.product.DTO.ProductInventoryRequest;
import com.noux.productservice.request.product.DTO.ProductPriceRange;
import com.noux.productservice.request.product.DTO.ProductPurchaseRequest;
import com.noux.productservice.request.product.DTO.ProductRequest;
import com.noux.productservice.request.product.mapper.ProductRequestMapper;
import com.noux.productservice.response.product.DTO.ProductResponse;
import com.noux.productservice.response.product.mapper.ProductResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    private final ProductRequestMapper productRequestMapper;

    private final ProductResponseMapper productResponseMapper;

    private final CategoryRepository categoryRepository;

    private  final CategoryRequestMapper categoryRequestMapper;

    public ProductService(ProductRepository productRepository ,
                          ProductRequestMapper productRequestMapper,
                          ProductResponseMapper productResponseMapper ,
                          CategoryRepository categoryRepository ,
                          CategoryRequestMapper categoryRequestMapper){
        this.productRepository=productRepository;
        this.productRequestMapper=productRequestMapper;
        this.productResponseMapper=productResponseMapper;
        this.categoryRepository=categoryRepository;
        this.categoryRequestMapper=categoryRequestMapper;
    }

    public ProductResponse createProduct(ProductRequest productRequest){
        var product = productRequestMapper.requestMapping(productRequest);
        var savedProduct = productRepository.save(product);
        return productResponseMapper.responseMapping(savedProduct);
    }

    public ProductResponse updateProduct(Long id , ProductRequest productRequest){
        var existingProduct= productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found "
                ));
        var request = productRequestMapper.requestMapping(productRequest);
        if (request.getName() != null) {
            existingProduct.setName(request.getName());
        }
        if (request.getDescription() != null){
            existingProduct.setDescription(request.getDescription());
        }
        if (request.getAvailableQuantity() >= 0) {
            existingProduct.setAvailableQuantity(request.getAvailableQuantity());
        }
        if (request.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            existingProduct.setPrice(request.getPrice());
        }
        if (request.getCategory() != null){
            existingProduct.setCategory(request.getCategory());
        }
        var savedProduct = productRepository.save(existingProduct);
        return productResponseMapper.responseMapping(savedProduct);

    }

    public void deleteProduct(Long id){
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found "
                ));
        productRepository.delete(existingProduct);
    }


    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productResponseMapper::responseMapping)
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id){
        var existingProduct= productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found "
                ));
        return productResponseMapper.responseMapping(existingProduct);
    }

    public List<ProductResponse> getProductsByCategory(CategoryRequest categoryRequest){
        var category = categoryRequestMapper.requestMapping(categoryRequest);
        var products = productRepository.findProductsByCategory_Name(category.getName());
        return products.stream()
                .map(productResponseMapper::responseMapping)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> filterProductsByPriceRange(ProductPriceRange productPriceRange){
        var products = productRepository.findProductsByPriceBetween(productPriceRange.minPrice() , productPriceRange.maxPrice());
        return products.stream()
                .map(productResponseMapper::responseMapping)
                .collect(Collectors.toList());
    }


    public ProductResponse adjustInventory(Long productId, ProductInventoryRequest productInventoryRequest) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

        double newQuantity = product.getAvailableQuantity() + productInventoryRequest.quantity();
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
        }
        product.setAvailableQuantity(newQuantity);
        var savedProduct = productRepository.save(product);
        return productResponseMapper.responseMapping(savedProduct);

    }
    public List<ProductResponse> purchaseProduct(List<ProductPurchaseRequest> requests) {
        var productIds = requests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var existingProducts = productRepository.findAllByIdIn(productIds);

        if (productIds.size() != existingProducts.size()) {
            throw new ProductPurchaseException("One or more products do not exist.");
        }

        var productMap = existingProducts.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        for (ProductPurchaseRequest request : requests) {
            Product product = productMap.get(request.productId());
            if (product == null) {
                throw new ProductNotFoundException("Product not found with id: " + request.productId());
            }

            if (product.getAvailableQuantity() < request.quantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            product.setAvailableQuantity(product.getAvailableQuantity() - request.quantity());
        }

        var savedProducts = productRepository.saveAll(existingProducts);

        return savedProducts.stream()
                .map(productResponseMapper::responseMapping)
                .collect(Collectors.toList());
    }


    public void  addProductToCategory(Long categoryId , Long productId){
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found "
                ));
        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found "
                ));

        product.setCategory(category);
//        category.getProducts().add(product);
        productRepository.save(product);
    }

    public void removeProductFromCategory(Long categoryId , Long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found "
                ));
        var category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found "
                ));
//        if (category.getProducts().contains(product)) {
//            category.getProducts().remove(product);
            product.setCategory(null);
            productRepository.save(product);
            categoryRepository.save(category);
//        }
    }

    public List<ProductResponse> getAllproductsOfCategory(Long idCategory){
        var category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found "
                ));
        List<Product> productsList = category.getProducts().stream().toList();
        return productsList.stream()
                .map(productResponseMapper::responseMapping)
                .collect(Collectors.toList());
    }
}
