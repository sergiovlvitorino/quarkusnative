package br.com.vitorino.infrastructure.exception;

public class UnprocessableEntityException extends RuntimeException{

    public UnprocessableEntityException(String message){
        super(message);
    }

}
