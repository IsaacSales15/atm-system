package com.sales.project.application.dto;

import java.math.BigDecimal;

public record AccountDTO(String accountNumber, String clientName, String pin, BigDecimal balance) {
    public AccountDTO {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser vazio");
        }
        if (clientName == null || clientName.isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio");
        }
        if (pin == null || pin.isBlank()) {
            throw new IllegalArgumentException("PIN não pode ser vazio");
        }
        if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo não pode ser negativo");
        }
    }

}
