package com.noux.clientservice.service;

import com.noux.clientservice.exception.ClientNotFoundException;
import com.noux.clientservice.entity.Client;
import com.noux.clientservice.request.ClientRequestMapper;
import com.noux.clientservice.response.ClientResponseMapper;
import com.noux.clientservice.repository.ClientRepository;
import com.noux.clientservice.request.ClientRequest;
import com.noux.clientservice.response.ClientResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientRequestMapper requestMapper;
    private final ClientResponseMapper responseMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ClientRequestMapper requestMapper,
                         ClientResponseMapper responseMapper){
        this.clientRepository=clientRepository;
        this.requestMapper=requestMapper;
        this.responseMapper=responseMapper;

    }

    public ClientResponse CreateClient(ClientRequest clientRequest){
        Client newClient = requestMapper.requestMapping(clientRequest);
        Client savedClient = clientRepository.save(newClient);
        return responseMapper.responseMapping(savedClient);
    }

    public ClientResponse updateClient(ClientRequest clientRequest, Long id) {
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("Cannot update client :: No client found with id : %s", id)
                ));

        if (clientRequest.getFirstName() != null) {
            client.setFirstName(clientRequest.getFirstName());
        }
        if (clientRequest.getLastName() != null) {
            client.setLastName(clientRequest.getLastName());
        }
        if (clientRequest.getEmail() != null) {
            client.setEmail(clientRequest.getEmail());
        }
        if (clientRequest.getAddress() != null) {
            client.setAddress(clientRequest.getAddress());
        }

        Client savedClient = clientRepository.save(client);
        return responseMapper.responseMapping(savedClient);
    }


    public void deleteClient(Long id){
         var client = clientRepository.findById(id)
                 .orElseThrow(() -> new ClientNotFoundException(
                         String.format("Cannot delete client :: No client found with id : %s " ,id)
                 ));
         clientRepository.delete(client);
    }

    public List<ClientResponse> getAllClients(){
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(responseMapper::responseMapping)
                .collect(Collectors.toList());

    }

    public ClientResponse findClientById(Long id){
        var client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("No client found with id : %s " ,id)
                ));
        return responseMapper.responseMapping(client);
    }

}
