package com.sales.project.domain.valueobject.account;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Balance {
    private BigDecimal value;

    protected Balance() {}

    public Balance(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Saldo inválido");
        }
        this.value = value;
    }

    public Balance add(Balance other) {
        return new Balance(this.value.add(other.value));
    }

    public Balance subtract(Balance other) {
        return new Balance(this.value.subtract(other.value));
    }

    public boolean lessThan(Balance other) {
        return this.value.compareTo(other.value) < 0;
    }

    public BigDecimal value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Balance b && Objects.equals(value, b.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

