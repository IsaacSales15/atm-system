package com.sales.project.application.service;

import com.sales.project.domain.model.Account;
import com.sales.project.domain.repository.AccountRepository;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.sales.project.domain.valueobject.account.Pin;
import com.sales.project.domain.valueobject.account.Balance;
import com.sales.project.domain.model.Client;

@Service
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account createAccount(Client client, String pin, BigDecimal balance) {
        if (repo.existsByAccountNumber(client.getAccountNumber())) {
            throw new IllegalArgumentException("Número da conta já cadastrado");
        }
        Pin accountPin = new Pin(pin);
        Balance accountBalance = new Balance(balance);
        return repo.save(new Account(client, accountPin, accountBalance));
    }

    public Account getAccountByNumber(String accountNumber) {
        Account account = repo.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Conta não encontrada com número: " + accountNumber);
        }
        return account;
    }

    public void deleteAccountByNumber(String accountNumber) {
        if (!repo.existsByAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Conta não encontrada com número: " + accountNumber);
        }
        repo.deleteByAccountNumber(accountNumber);
    }

    public Account updateAccountPin(String accountNumber, String newPin) {
        Account account = getAccountByNumber(accountNumber);
        Pin updatedPin = new Pin(newPin);
        account.updatePin(updatedPin);
        return repo.save(account);
    }

    public Account updateAccountBalance(String accountNumber, BigDecimal newBalance) {
        Account account = getAccountByNumber(accountNumber);
        Balance updatedBalance = new Balance(newBalance);
        account.updateBalance(updatedBalance);
        return repo.save(account);
    }

}
