package com.noux.orderservice.proxy.Product;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)

public class PurchaseResponse {
    @NotNull
    private Long productId;

    @Positive
    @NotNull
    private double qte ;

    @NotNull
    private String name ;

    @NotNull
    private String description ;

    @NotNull
    private BigDecimal price ;


}
