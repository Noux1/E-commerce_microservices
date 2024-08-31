package com.noux.clientservice.controller;

import com.noux.clientservice.exception.ClientNotFoundException;
import com.noux.clientservice.request.ClientRequest;
import com.noux.clientservice.response.ClientErrorResponse;
import com.noux.clientservice.response.ClientResponse;
import com.noux.clientservice.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.version}/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {

        this.clientService=clientService;

    }

    @PostMapping()
    public ResponseEntity<Object> createClient(@RequestBody @Valid ClientRequest clientRequest){
        try {
            return ResponseEntity.ok(clientService.CreateClient(clientRequest));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClientErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClientErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody @Valid ClientRequest request , @PathVariable("id") Long id ){
        try {
            return ResponseEntity.ok(clientService.updateClient(request,id));

        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ClientErrorResponse("Client not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClientErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClientErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable("id") Long id){

        try {
            clientService.deleteClient(id);
            return ResponseEntity.accepted().build();
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ClientErrorResponse("Client not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClientErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClientErrorResponse("Internal server error", e.getMessage()));
        }
    }

    @GetMapping()
    public ResponseEntity<Object> findAll(){
            return ResponseEntity.ok(clientService.getAllClients());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findOne(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(clientService.findClientById(id));

        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ClientErrorResponse("Client not found", e.getMessage()));
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ClientErrorResponse("Validation failed", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ClientErrorResponse("Internal server error", e.getMessage()));
        }
    }
}
