package com.noux.orderservice.proxy.Payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "product-service",
        url = "${application.config.payment-service-url}"
)
public interface PaymentProxy {
    @PostMapping
    Long requestOrderPayment(@RequestBody PaymentRequest request);
}
