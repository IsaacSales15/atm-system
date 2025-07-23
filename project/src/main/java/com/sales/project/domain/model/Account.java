package com.sales.project.domain.model;

import com.sales.project.domain.valueobject.account.Balance;
import com.sales.project.domain.valueobject.account.Name;
import com.sales.project.domain.valueobject.account.Pin;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Pin pin;

    @Embedded
    private Balance balance;

    protected Account() {}

    public Account(Name name, Pin pin, Balance balance) {
        this.name = name;
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
