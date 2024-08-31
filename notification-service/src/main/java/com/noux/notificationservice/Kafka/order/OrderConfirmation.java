package com.noux.notificationservice.Kafka.order;

import com.noux.notificationservice.Kafka.payment.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation{
     String orderReference;
     BigDecimal totalAmount;
     PaymentMethod paymentMethod;
     Client client;
      List<Product> products ;


}