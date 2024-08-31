package com.noux.notificationservice.Kafka.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product{
     Integer productId;
     String name;
      String description;
     BigDecimal price;
      double quantity;

}