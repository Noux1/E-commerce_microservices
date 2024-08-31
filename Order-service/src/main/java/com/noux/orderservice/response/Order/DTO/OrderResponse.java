package com.noux.orderservice.response.Order.DTO;

import com.noux.orderservice.entity.OrderDetail;
import com.noux.orderservice.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    @NotNull
    private Long id;

    @NotNull
    private String reference;

    @NotNull
    private BigDecimal totalAmount;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long clientId;

}
