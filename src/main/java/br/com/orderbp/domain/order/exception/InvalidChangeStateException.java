package br.com.orderbp.domain.order.exception;

public class InvalidChangeStateException extends RuntimeException{

    public InvalidChangeStateException(String message){
        super(message);
    }
}
