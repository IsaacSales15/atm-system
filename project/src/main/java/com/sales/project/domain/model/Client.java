package com.sales.project.domain.model;

import com.sales.project.domain.valueobject.client.Name;

import java.util.ArrayList;
import java.util.List;

import com.sales.project.domain.valueobject.client.Cpf;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) 
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name_value", nullable = false))
    private Name name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "cpf_value", nullable = false))
    private Cpf cpf;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts = new ArrayList<>();

    protected Client() {}

    public Client(Name name, Cpf cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public void addAccount(Account account) {
        if (account == null || accounts.contains(account)) {
            throw new IllegalArgumentException("Conta inválida ou já existente");
        }
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        if (account == null || !accounts.contains(account)) {
            throw new IllegalArgumentException("Conta inválida ou não existente");
        }
        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void updateName(Name newName) {
        if (newName == null || this.name.equals(newName)) {
            throw new IllegalArgumentException("Nome inválido ou já existente");
        }
        this.name = newName;
    }

    public void updateCpf(Cpf newCpf) {
        if (newCpf == null || this.cpf.equals(newCpf)) {
            throw new IllegalArgumentException("CPF inválido ou já existente");
        }
        this.cpf = newCpf;
    }

    public Long getId() {
        return id;
    }

}
