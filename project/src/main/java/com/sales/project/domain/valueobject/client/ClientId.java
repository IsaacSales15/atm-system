package com.sales.project.domain.valueobject.client;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ClientId {

    @Column(name = "client_id")
    private Long value;

    protected ClientId() {}

    public ClientId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientId)) return false;
        ClientId clientId = (ClientId) o;
        return Objects.equals(value, clientId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
