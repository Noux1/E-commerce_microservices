package com.noux.orderservice.exception;


import com.noux.orderservice.response.Order.DTO.OrderErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleClientNotFoundException(OrderNotFoundException ex, WebRequest request) {
        OrderErrorResponse errorResponse = new OrderErrorResponse("Order not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        OrderErrorResponse errorResponse = new OrderErrorResponse("Validation failed", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        OrderErrorResponse errorResponse = new OrderErrorResponse("Internal server error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}