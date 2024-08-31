package com.noux.productservice.controller;
import com.noux.productservice.exception.CategoryNotFoundException;
import com.noux.productservice.request.category.DTO.CategoryRequest;
import com.noux.productservice.response.category.DTO.CategoryErrorResponse;
import com.noux.productservice.response.category.DTO.CategoryResponse;
import com.noux.productservice.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.version}/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService= categoryService;
    }

    @PostMapping()
    public ResponseEntity<Object> createCategory(@RequestBody @Valid CategoryRequest categoryRequest){

        try {
            CategoryResponse createdCategory = categoryService.createCategory(categoryRequest);
            return ResponseEntity.ok(createdCategory);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CategoryErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable("id") Long id ,
                                                 @RequestBody @Valid CategoryRequest categoryRequest){

        try {
            return ResponseEntity.ok(categoryService.updateCategory(id,categoryRequest));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryErrorResponse("Category not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CategoryErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id){

        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.accepted().build();
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryErrorResponse("Category not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CategoryErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAll(){

        try {
            return ResponseEntity.ok(categoryService.getAllCategories());
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryErrorResponse("Category not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CategoryErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable("id") Long id){

        try {
            return ResponseEntity.ok(categoryService.getCategory(id));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryErrorResponse("Category not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CategoryErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryErrorResponse("Internal server error", e.getMessage()));
        }

    }

    @GetMapping("/name")
    public ResponseEntity<Object> findByName(String Name){

        try {
            return   ResponseEntity.ok(categoryService.findCategoryByName(Name));
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryErrorResponse("Category not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CategoryErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryErrorResponse("Internal server error", e.getMessage()));
        }
    }
}


