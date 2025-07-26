package com.sales.project.adapter.web;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sales.project.domain.repository.ClientRepository;
import com.sales.project.application.service.ClientService;
import com.sales.project.application.dto.ClientDTO;
import com.sales.project.domain.model.Client;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
    }

    @GetMapping
    public List<Client> getAll() {
        return clientRepository.findAll();
    }
    
}
