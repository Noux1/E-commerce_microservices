package com.noux.clientservice.response;

import com.noux.clientservice.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;

}
