package com.noux.paymentservice.response.mapper;

import com.noux.paymentservice.entity.Payment;
import com.noux.paymentservice.response.DTO.PaymentResponse;
import org.springframework.stereotype.Component;

@Component

public class PaymentResponseMapper {

    public PaymentResponse responseMapping(Payment payment){
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(payment.getId());
        paymentResponse.setAmount(payment.getAmount());
        paymentResponse.setPaymentMethod(payment.getPaymentMethod());
        paymentResponse.setOrderId(payment.getOrderId());
        paymentResponse.setOrderRef(payment.getReference());
        return paymentResponse;
    }

}
