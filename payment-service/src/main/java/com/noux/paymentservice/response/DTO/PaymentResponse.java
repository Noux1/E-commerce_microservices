package com.noux.paymentservice.response.DTO;

import com.noux.paymentservice.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private Long orderId;

    private String orderRef;

}
