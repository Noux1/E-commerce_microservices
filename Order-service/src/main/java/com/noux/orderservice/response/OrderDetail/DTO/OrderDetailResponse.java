package com.noux.orderservice.response.OrderDetail.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    @NotNull
    private Long id ;

    @NotNull
    private double qte;


}
