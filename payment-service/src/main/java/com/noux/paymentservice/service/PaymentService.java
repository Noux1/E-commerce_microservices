package com.noux.paymentservice.service;

import com.noux.paymentservice.entity.Payment;
import com.noux.paymentservice.event.PaymentNotifyEvent;
import com.noux.paymentservice.repository.PaymentRepository;
import com.noux.paymentservice.request.DTO.PaymentRequest;
import com.noux.paymentservice.request.mapper.PaymentRequestMapper;
import com.noux.paymentservice.response.DTO.PaymentResponse;
import com.noux.paymentservice.response.mapper.PaymentResponseMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentRequestMapper paymentRequestMapper;

    private final PaymentResponseMapper paymentResponseMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentResponseMapper paymentResponseMapper,
                          PaymentRequestMapper paymentRequestMapper , KafkaTemplate<String, Object> kafkaTemplate){
        this.paymentRepository=paymentRepository;
        this.paymentRequestMapper=paymentRequestMapper;
        this.paymentResponseMapper=paymentResponseMapper;
        this.kafkaTemplate=kafkaTemplate;
    }


    public PaymentResponse createPayment(PaymentRequest paymentRequest){
        Payment newPayment = paymentRequestMapper.requestMapping(paymentRequest);
        newPayment.setCreatedDate(LocalDateTime.now());
        newPayment.setLastModifiedDate(LocalDateTime.now());
        Payment savedPayment = paymentRepository.save(newPayment);
        PaymentNotifyEvent paymentNotifyEvent = PaymentNotifyEvent.builder()
                .paymentMethod(paymentRequest.getPaymentMethod())
                .amount(paymentRequest.getAmount())
                .client(paymentRequest.getClient())
                .orderRef(paymentRequest.getOrderRef())
                .build();

        MessageBuilder<PaymentNotifyEvent> messageBuilder = MessageBuilder
                .withPayload(paymentNotifyEvent)
                .setHeader(TOPIC, "payment-topic");

        kafkaTemplate.send(messageBuilder.build());

        return paymentResponseMapper.responseMapping(savedPayment);
    }

}
