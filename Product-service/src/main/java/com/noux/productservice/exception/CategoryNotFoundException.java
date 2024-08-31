package com.noux.productservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryNotFoundException extends RuntimeException {
        private final String message;

        public CategoryNotFoundException(String message) {
                super(message);
                this.message = message;
        }
}

