package com.sales.project.domain.valueobject.client;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Cpf {
    private String value;

    protected Cpf() {}

    public Cpf(String value) {
        if (value == null || value.trim().isEmpty() || !value.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Cpf c && Objects.equals(value, c.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
