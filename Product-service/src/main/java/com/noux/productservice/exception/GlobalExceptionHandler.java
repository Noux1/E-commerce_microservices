package com.noux.productservice.exception;


import com.noux.productservice.response.category.DTO.CategoryErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex, WebRequest request) {
        CategoryErrorResponse errorResponse = new CategoryErrorResponse("Category not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        CategoryErrorResponse errorResponse = new CategoryErrorResponse("Product not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ProductPurchaseException.class)
    public ResponseEntity<Object> handleProductPurchaseException(ProductPurchaseException ex, WebRequest request) {
        CategoryErrorResponse errorResponse = new CategoryErrorResponse("Product purchase error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        CategoryErrorResponse errorResponse = new CategoryErrorResponse("Validation failed", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        CategoryErrorResponse errorResponse = new CategoryErrorResponse("Internal server error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
