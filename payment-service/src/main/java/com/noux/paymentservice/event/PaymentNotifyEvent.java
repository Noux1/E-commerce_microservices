package com.noux.paymentservice.event;

import com.noux.paymentservice.enums.PaymentMethod;
import com.noux.paymentservice.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentNotifyEvent {
    Client client;

    String orderRef;

    BigDecimal amount;

    PaymentMethod paymentMethod;

}
