package com.sales.project.application.service;

import com.sales.project.domain.exception.account.AccountAlreadyExistsException;
import com.sales.project.domain.exception.account.AccountNotFoundException;
import com.sales.project.domain.model.Account;
import com.sales.project.domain.repository.AccountRepository;
import com.sales.project.domain.model.Client;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.sales.project.domain.valueobject.account.*;
import com.sales.project.domain.valueobject.client.ClientId;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ClientService clientService;

    public AccountService(AccountRepository accountRepository, ClientService clientSerice) {
        this.accountRepository = accountRepository;
        this.clientService = clientSerice;
    }

    public Account createAccount(ClientId clientId, String pin, BigDecimal balance, String accountNumber) {
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            throw new AccountAlreadyExistsException(accountNumber);
        }

        Client client = clientService.getById(clientId);
        Pin newPin = new Pin(pin);
        Balance newBalance = new Balance(balance);
        AccountNumber number = new AccountNumber(accountNumber);

        return accountRepository.save(new Account(client, newPin, newBalance, number));
    }

    public Account getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return account;
    }

    public void deleteAccountByNumber(String accountNumber) {
        if (!accountRepository.existsByAccountNumber(accountNumber)) {
            throw new AccountNotFoundException(accountNumber);
        }
        accountRepository.deleteByAccountNumber(accountNumber);
    }

    public Account updateAccountPin(String accountNumber, String newPin) {
        Account account = getAccountByNumber(accountNumber);
        account.updatePin(new Pin(newPin));
        return accountRepository.save(account);
    }

    public Account updateAccountBalance(String accountNumber, BigDecimal newBalance) {
        Account account = getAccountByNumber(accountNumber);
        account.updateBalance(new Balance(newBalance));
        return accountRepository.save(account);
    }
}
