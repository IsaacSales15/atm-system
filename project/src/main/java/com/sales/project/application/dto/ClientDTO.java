package com.sales.project.application.dto;


public record ClientDTO(String name, String cpf) {
    public ClientDTO {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }
    }
}
