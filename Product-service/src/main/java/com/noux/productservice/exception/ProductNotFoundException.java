package com.noux.productservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductNotFoundException extends RuntimeException {
    private final String message;

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
