package com.sales.project.domain.repository;

import com.sales.project.domain.model.Client;
import com.sales.project.domain.valueobject.client.Cpf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCpf(Cpf cpf);

    boolean existsByCpf(Cpf cpf);

    boolean existsByName(String name);

    void deleteByCpf(String cpf);

    void deleteByName(String name);

    Client findByName(String name);
}
