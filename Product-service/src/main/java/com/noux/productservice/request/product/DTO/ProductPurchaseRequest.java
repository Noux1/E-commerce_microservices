package com.noux.productservice.request.product.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;


public record ProductPurchaseRequest (
    @NotNull
    Long productId,
    @NotNull
   double quantity
){  }
