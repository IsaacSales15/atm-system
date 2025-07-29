package com.sales.project.adapter.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sales.project.domain.exception.client.CpfAlreadyExistsException;
import com.sales.project.domain.exception.client.InvalidClientException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<String> handleCpfExists(CpfAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    
    @ExceptionHandler(InvalidClientException.class)
    public ResponseEntity<String> handleInvalidClientException(InvalidClientException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

     @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Erro inesperado: " + ex.getMessage());
    }

}
