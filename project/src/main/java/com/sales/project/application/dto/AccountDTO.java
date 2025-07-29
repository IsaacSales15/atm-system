package com.sales.project.application.dto;

import java.math.BigDecimal;

public record AccountDTO(Long clientId, String pin, BigDecimal balance, String accountNumber) {
    public AccountDTO {
        if (clientId == null) {
            throw new IllegalArgumentException("ID do cliente não pode ser nulo");
        }
        if (pin == null || pin.isBlank()) {
            throw new IllegalArgumentException("PIN não pode ser vazio");
        }
        if (balance == null) {
            throw new IllegalArgumentException("Saldo não pode ser nulo");
        }
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser vazio");
        }
    }

}
