package com.sales.project.adapter.web.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.sales.project.application.service.AccountService;
import com.sales.project.application.dto.AccountDTO;
import com.sales.project.domain.model.Account;
import com.sales.project.domain.valueobject.client.ClientId;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO dto) {
        ClientId clientId = new ClientId(dto.clientId());
        Account account = service.createAccount(clientId, dto.pin(), dto.balance(), dto.accountNumber());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account account = service.getAccountByNumber(accountNumber);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/{accountNumber}/pin")
    public ResponseEntity<Account> updateAccountPin(@PathVariable String accountNumber, @RequestBody AccountDTO dto) {
        Account updatedAccount = service.updateAccountPin(accountNumber, dto.pin());
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @PutMapping("/{accountNumber}/balance")
    public ResponseEntity<Account> updateAccountBalance(@PathVariable String accountNumber, @RequestBody AccountDTO dto) {
        Account updatedAccount = service.updateAccountBalance(accountNumber, dto.balance());
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccountByNumber(@PathVariable String accountNumber) {
        service.deleteAccountByNumber(accountNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
