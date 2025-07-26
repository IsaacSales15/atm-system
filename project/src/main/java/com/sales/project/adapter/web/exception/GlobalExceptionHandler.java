package com.sales.project.adapter.web.exception;

import com.sales.project.domain.exception.CpfAlreadyExistsException;
import com.sales.project.domain.exception.InvalidClientException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
