package com.noux.orderservice.proxy.Client;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ClientResponse {
    private Long id ;
    private String firstName;
    private String lastName;
    private String email;
}
