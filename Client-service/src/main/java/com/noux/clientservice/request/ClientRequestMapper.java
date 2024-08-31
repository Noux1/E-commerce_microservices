package com.noux.clientservice.request;

import com.noux.clientservice.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper {
    public Client requestMapping(ClientRequest request){
        return Client.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();
    }
}
