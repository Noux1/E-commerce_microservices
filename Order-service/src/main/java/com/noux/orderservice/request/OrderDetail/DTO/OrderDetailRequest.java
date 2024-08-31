package com.noux.orderservice.request.OrderDetail.DTO;

import com.noux.orderservice.entity.Order;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {
    private Long id ;

    @NotNull
    private Long OrderId;

    @NotNull
    private double qte;

    @NotNull
    private Long productId;
}
