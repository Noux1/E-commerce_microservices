package com.noux.orderservice.proxy.Payment;

import com.noux.orderservice.enums.PaymentMethod;
import com.noux.orderservice.proxy.Client.ClientResponse;

import java.math.BigDecimal;

public class PaymentRequest {
    BigDecimal amount;
    PaymentMethod paymentMethod;
    Long orderId;
    String orderReference;
    ClientResponse clientResponse;
}
