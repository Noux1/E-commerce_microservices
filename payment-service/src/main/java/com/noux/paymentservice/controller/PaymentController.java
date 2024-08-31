package com.noux.paymentservice.controller;

import com.noux.paymentservice.request.DTO.PaymentRequest;
import com.noux.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.version}/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping()
    public ResponseEntity<Object> createPayment(@RequestBody @Valid PaymentRequest paymentRequest){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequest));
    }
}
