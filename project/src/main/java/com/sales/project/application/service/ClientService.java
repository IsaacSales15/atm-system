package com.sales.project.application.service;

import com.sales.project.domain.exception.client.CpfAlreadyExistsException;
import com.sales.project.domain.exception.client.InvalidClientException;
import com.sales.project.domain.model.Client;
import com.sales.project.domain.repository.ClientRepository;
import com.sales.project.domain.valueobject.client.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;
    
    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    public Client createClient(String name, String cpf) {
        if(repo.existsByCpf(new Cpf(cpf))) {
            throw new CpfAlreadyExistsException("CPF já cadastrado");
        }
        Name clientName = new Name(name);
        Cpf clientCpf = new Cpf(cpf);
        return repo.save(new Client(clientName, clientCpf));
    }

     public void deleteByCpf(String cpf) {
        repo.deleteByCpf(cpf);
    }

    public Client getByCpf(String cpf) {
        Cpf clientCpf = new Cpf(cpf);
        Client client = repo.findByCpf(clientCpf);
        if (client == null) {
            throw new InvalidClientException("Cliente não encontrado com CPF: " + cpf);
        }
        return client;
    }

    public Client getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new InvalidClientException("Cliente não encontrado com ID: " + id));
    }

    public Client getById(ClientId clientId) {
        return repo.findById(clientId.getValue())
                .orElseThrow(() -> new InvalidClientException("Cliente não encontrado com ID: " + clientId.getValue()));
    }

    public List<Client> getAllClients() {
        return repo.findAll();
    }

    public Client updateClient(Long id, String name, String cpf) {
        var existingClient = repo.findById(id);
        Client client = existingClient
                .orElseThrow(() -> new InvalidClientException("Cliente não encontrado com ID: " + id));

       client.updateName(new Name(name));
       client.updateCpf(new Cpf(cpf)); 
       
       return repo.save(client);
    }
}
