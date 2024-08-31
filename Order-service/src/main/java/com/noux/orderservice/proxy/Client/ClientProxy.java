package com.noux.orderservice.proxy.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "client-service",
        url = "${application.config.client-service-url}"
)
public interface ClientProxy {
    @GetMapping("/{id}")
    Optional<ClientResponse> findClientById(@PathVariable("id") Long clientId);
}