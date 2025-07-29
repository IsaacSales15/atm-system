package com.sales.project.domain.exception.client;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String message) {
        super(message);
    }

    public CpfAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
