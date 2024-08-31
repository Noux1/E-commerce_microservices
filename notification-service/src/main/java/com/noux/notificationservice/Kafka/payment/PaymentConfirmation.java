package com.noux.notificationservice.Kafka.payment;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentConfirmation{
     String orderReference;
     BigDecimal amount;
     PaymentMethod paymentMethod;
     String clientFirstname;
     String clientLastname;
     String clientEmail;
}