package com.sales.project.adapter.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sales.project.application.service.ClientService;
import com.sales.project.application.dto.ClientDTO;
import com.sales.project.domain.model.Client;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Client> createEntity(@RequestBody ClientDTO clientDTO) {
        Client client = service.createClient(clientDTO.name(), clientDTO.cpf());
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Client>> getAllEntity() {
        List<Client> clients = service.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Client> getByCpf(@PathVariable String cpf) {
        Client client = service.getByCpf(cpf);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateEntity(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Client updatedClient = service.updateClient(id, clientDTO.name(), clientDTO.cpf());
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteByCpf(@PathVariable String cpf) {
        service.deleteByCpf(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
