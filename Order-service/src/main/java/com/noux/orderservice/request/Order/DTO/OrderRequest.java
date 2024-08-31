package com.noux.orderservice.request.Order.DTO;

import com.noux.orderservice.enums.OrderStatus;
import com.noux.orderservice.enums.PaymentMethod;
import com.noux.orderservice.proxy.Product.PurchaseRequest;
import com.noux.orderservice.request.OrderDetail.DTO.OrderDetailRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {


    private Long id ;

    @NotNull
    private String reference;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long clientId;

    @NotNull
    private BigDecimal totalAmount;

    @NotEmpty
    private List<PurchaseRequest> products;
}
