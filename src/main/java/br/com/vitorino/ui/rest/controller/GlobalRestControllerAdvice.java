package br.com.vitorino.ui.rest.controller;


import br.com.vitorino.infrastructure.exception.NotFoundException;
import br.com.vitorino.infrastructure.exception.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity handleException(UnprocessableEntityException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(String.format("{\"reason\":\"%s\"}", exception.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("{\"reason\":\"%s\"}", exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("{\"reason\":\"%s\"}", exception.getMessage()));
    }

}
