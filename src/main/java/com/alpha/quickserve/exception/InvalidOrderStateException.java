package com.alpha.quickserve.exception;

public class InvalidOrderStateException extends RuntimeException{

    public InvalidOrderStateException(String message){
        super(message);
    }

}