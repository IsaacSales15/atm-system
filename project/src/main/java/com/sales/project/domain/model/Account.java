package com.sales.project.domain.model;

import com.sales.project.domain.valueobject.account.*;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Embedded
    @AttributeOverride(name = "value", column = @Column( name = "pin_value", nullable = false))
    private Pin pin;

    @Embedded
    @AttributeOverride(name = "value", column = @Column( name = "balance_value", nullable = false))
    private Balance balance;

    protected Account() {}

    public Account(Client client, Pin pin, Balance balance) {
        this.client = client;
        this.pin = pin;
        this.balance = balance;
    }

    public void deposit(Balance amount) {
        this.balance = balance.add(amount);
    }

    public void withdraw(Balance amount) {
        if(balance.lessThan(amount)){
            throw new IllegalStateException("Saldo insuficiente");
        }
        this.balance = balance.subtract(amount);
    }

    public void validatePin(Pin pinInput) {
        if (!this.pin.equals(pinInput)) {
            throw new IllegalArgumentException("PIN inválido");
        }
    }

    public Balance getBalance(){
        return balance;
    }
}
