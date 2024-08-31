package com.noux.orderservice.proxy.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseRequest {

    @NotNull
    private Long productId;

    @Positive
    @NotNull
    private double qte ;

}
