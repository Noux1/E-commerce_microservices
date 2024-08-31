package com.noux.clientservice.exception;


import com.noux.clientservice.response.ClientErrorResponse;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
        ClientErrorResponse errorResponse = new ClientErrorResponse("Client not found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        ClientErrorResponse errorResponse = new ClientErrorResponse("Validation failed", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        ClientErrorResponse errorResponse = new ClientErrorResponse("Internal server error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
