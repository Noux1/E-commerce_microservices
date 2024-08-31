package com.noux.paymentservice.request.mapper;

import com.noux.paymentservice.entity.Payment;
import com.noux.paymentservice.request.DTO.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentRequestMapper {

    public Payment requestMapping(PaymentRequest request){
        if (request == null){
            return null;
        }
        return Payment.builder()
                .id(request.getId())
                .reference(request.getOrderRef())
                .paymentMethod(request.getPaymentMethod())
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .build();
    }
}
