package com.noux.productservice.controller;
import com.noux.productservice.exception.ProductNotFoundException;
import com.noux.productservice.request.category.DTO.CategoryRequest;
import com.noux.productservice.request.product.DTO.ProductInventoryRequest;
import com.noux.productservice.request.product.DTO.ProductPriceRange;
import com.noux.productservice.request.product.DTO.ProductPurchaseRequest;
import com.noux.productservice.request.product.DTO.ProductRequest;
import com.noux.productservice.response.product.DTO.ProductErrorResponse;
import com.noux.productservice.response.product.DTO.ProductResponse;
import com.noux.productservice.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping()
    public ResponseEntity<Object> createProduct(@RequestBody @Valid ProductRequest productRequest){

        try {
            ProductResponse createdProduct = productService.createProduct(productRequest);
            return ResponseEntity.ok(createdProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid ProductRequest productRequest,
                                                @PathVariable("id") Long id){

        try {
            return ResponseEntity.ok(productService.updateProduct(id,productRequest));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id){

        try {
            productService.deleteProduct(id);
            return ResponseEntity.accepted().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findALL(){

        try {
            return  ResponseEntity.ok(productService.getAllProducts());

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable("id") Long id){

        try {
            return ResponseEntity.ok(productService.getProduct(id));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping("/category-name")
    public ResponseEntity<Object> getProductsByCategory(@RequestBody CategoryRequest categoryRequest){

        try {
            return ResponseEntity.ok(productService.getProductsByCategory(categoryRequest));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }


    @GetMapping("/price-range")
    public ResponseEntity<Object> filterProductsByPriceRange(@RequestBody ProductPriceRange productPriceRange){

        try {
            return ResponseEntity.ok(productService.filterProductsByPriceRange(productPriceRange));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @PutMapping("/{product-id}/adjustInventory")
    public ResponseEntity<Object> adjustInventory(@PathVariable("product-id") Long productId,
                                                  @RequestBody ProductInventoryRequest productInventoryRequest) {

        try {
            return ResponseEntity.ok(productService.adjustInventory(productId , productInventoryRequest));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object>  purchaseProducts(@RequestBody List<ProductPurchaseRequest> request){

        try {
            return ResponseEntity.ok(productService.purchaseProduct(request));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }


    @PutMapping("/addToCategory/{product-id}/{category-id}")
    public ResponseEntity<Object> addProductToCategory(@PathVariable("product-id") Long idP ,
                                                       @PathVariable("category-id") Long idC ){

        try {
            productService.addProductToCategory(idC , idP);
            return ResponseEntity.accepted().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @PutMapping("/removeFromCategory/{product-id}/{category-id}")
    public ResponseEntity<Object> removeProductFromCategory(@PathVariable("product-id") Long idP ,
                                                            @PathVariable("category-id") Long idC ){

        try {
            productService.removeProductFromCategory(idC , idP);
            return ResponseEntity.accepted().build();
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping("/AllOfCategory/{category-id}")
    public ResponseEntity<Object> getAllProductsInCategory(@PathVariable("category-id") Long idC){

        try {
            return ResponseEntity.ok(productService.getAllproductsOfCategory(idC));

        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ProductErrorResponse("Product not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ProductErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ProductErrorResponse("Internal server error", e.getMessage()));
        }
    }


}
