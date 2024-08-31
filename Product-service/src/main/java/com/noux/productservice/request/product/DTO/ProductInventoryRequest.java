package com.noux.productservice.request.product.DTO;

import jakarta.validation.constraints.NotNull;

public record ProductInventoryRequest(
        @NotNull
        double quantity

){  }
