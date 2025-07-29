package com.sales.project.domain.exception.account;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountNumber) {
        super("Conta não encontrada com número: " + accountNumber);
    }
    
}
