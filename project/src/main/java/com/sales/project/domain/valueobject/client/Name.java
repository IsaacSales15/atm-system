package com.sales.project.domain.valueobject.client;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    private String value;

    protected Name() {}

    public Name(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o){
        return o instanceof Name n && Objects.equals(value, n.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
