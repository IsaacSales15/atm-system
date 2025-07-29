package com.sales.project.domain.valueobject.account;

import jakarta.persistence.Embeddable;

@Embeddable
public class AccountNumber {
    private String value;

    protected AccountNumber() {}

    public AccountNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Número da conta não pode ser vazio");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AccountNumber an && value.equals(an.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "AccountNumber{" +
                "value='" + value + '\'' +
                '}';
    }
}
