package com.noux.paymentservice.request.DTO;

import com.noux.paymentservice.enums.PaymentMethod;
import com.noux.paymentservice.client.Client;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private Long id ;

    @NotNull
    private BigDecimal amount ;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    private Long orderId;

    @NotNull
    private String orderRef;

    @NotNull
    private Client client;

}
