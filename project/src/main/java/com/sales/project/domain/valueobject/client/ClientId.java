package com.sales.project.domain.valueobject.client;

import java.util.Objects;

public class ClientId {
    private final String value;

    public ClientId(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Client ID cannot be null or blank");
        }
        this.value = value;
    }

    public String getValue() {
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
        return "ClientId{" +
                "value='" + value + '\'' +
                '}';
    }
}
