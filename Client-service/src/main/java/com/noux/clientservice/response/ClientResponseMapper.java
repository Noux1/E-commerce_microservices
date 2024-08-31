package com.noux.clientservice.response;

import com.noux.clientservice.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseMapper {
    public ClientResponse responseMapping(Client client){
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setFirstName(client.getFirstName());
        clientResponse.setLastName(client.getLastName());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setAddress(client.getAddress());
        return clientResponse;
    }

}
