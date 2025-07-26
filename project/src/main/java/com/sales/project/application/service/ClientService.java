package com.sales.project.application.service;

import com.sales.project.domain.model.Client;
import com.sales.project.domain.repository.ClientRepository;
import com.sales.project.domain.valueobject.client.Name;
import com.sales.project.domain.valueobject.client.Cpf;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;
    
    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    public Client createClient(String name, String cpf) {
        if(repo.existsByCpf(cpf)) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        Name clientName = new Name(name);
        Cpf clientCpf = new Cpf(cpf);
        return repo.save(new Client(clientName, clientCpf));
    }

     public void deleteByCpf(String cpf) {
        repo.deleteByCpf(cpf);
    }

    public Client getByCpf(String cpf) {
        Client client = repo.findByCpf(cpf);
        if (client == null) {
            throw new IllegalArgumentException("Cliente não encontrado com CPF: " + cpf);
        }
        return client;
    }

    public List<Client> getAllClients() {
        return repo.findAll();
    }

    public Client updateClient(Long id, String name, String cpf) {
        var existingClient = repo.findById(id);
        Client client = existingClient
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

       client.updateName(new Name(name));
       client.updateCpf(new Cpf(cpf)); 
       
       return repo.save(client);
    }
}
