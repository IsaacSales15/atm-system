package com.sales.project.domain.model;

import com.sales.project.domain.valueobject.account.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private AccountNumber accountNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "pin_value", nullable = false))
    private Pin pin;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "balance_value", nullable = false))
    private Balance balance;

    protected Account() {
    }

    public Account(Client client, Pin pin, Balance balance, AccountNumber accountNumber) {
        this.client = client;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = generateAccountNumber();
    }

    private AccountNumber generateAccountNumber() {
        return new AccountNumber("ACCT-" + System.currentTimeMillis());
    }

    public void deposit(Balance amount) {
        this.balance = balance.add(amount);
    }

    public void withdraw(Balance amount) {
        if (balance.lessThan(amount)) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        this.balance = balance.subtract(amount);
    }

    public void validatePin(Pin pinInput) {
        if (!this.pin.equals(pinInput)) {
            throw new IllegalArgumentException("PIN inválido");
        }
    }

    public void updatePin(Pin newPin) {
        if (newPin == null || this.pin.equals(newPin)) {
            throw new IllegalArgumentException("PIN inválido ou já existente");
        }
        this.pin = newPin;
    }

    public void updateBalance(Balance newBalance) {
        if (newBalance == null || newBalance.value().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo inválido");
        }
        this.balance = newBalance;
    }

    public Balance getBalance() {
        return balance;
    }

}
