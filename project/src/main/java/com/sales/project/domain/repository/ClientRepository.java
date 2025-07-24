package com.sales.project.domain.repository;

import com.sales.project.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

    void deleteByCpf(String cpf);

    void deleteByName(String name);

    Client findByName(String name);
}
