package com.sales.project.adapter.web.controller;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import com.sales.project.application.service.AccountService;
import com.sales.project.application.dto.AccountDTO;
import com.sales.project.domain.model.Account;
import com.sales.project.domain.model.Client;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO dto) {
        Account account = service.createAccount(dto.accountNumber(), dto.clientName(), dto.pin(), dto.balance());
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

}
