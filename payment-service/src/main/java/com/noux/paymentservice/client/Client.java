package com.noux.paymentservice.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    private Long id ;

    private String firstName;

    private String lastName;

    private String email;
}
