package com.sales.project.domain.exception.account;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String accountNumber) {
        super("Conta já existe com o número: " + accountNumber);
    }
    
}
