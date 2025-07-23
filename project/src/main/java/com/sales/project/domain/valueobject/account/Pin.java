package com.sales.project.domain.valueobject.account;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Pin {
    private String value;

    protected Pin() {}

    public Pin(String value) {
        if (value == null || !value.matches("\\d{4}")) {
            throw new IllegalArgumentException("PIN deve ter 4 dígitos numéricos");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Pin pin && Objects.equals(value, pin.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
