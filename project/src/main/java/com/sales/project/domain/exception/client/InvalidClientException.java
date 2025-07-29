package com.sales.project.domain.exception.client;

public class InvalidClientException extends RuntimeException {
    
    public InvalidClientException(String message) {
        super(message);
    }

    public InvalidClientException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
