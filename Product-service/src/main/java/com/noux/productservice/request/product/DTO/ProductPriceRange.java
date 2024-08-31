package com.noux.productservice.request.product.DTO;


import java.math.BigDecimal;

public record ProductPriceRange (
    BigDecimal minPrice,
    BigDecimal maxPrice
){  }
