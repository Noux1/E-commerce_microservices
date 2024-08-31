package com.noux.notificationservice.Kafka.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client{
         String id;
         String firstname;
         String lastname;
         String email;


}