package com.noux.clientservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientNotFoundException extends RuntimeException {
    private final String message ;
}
