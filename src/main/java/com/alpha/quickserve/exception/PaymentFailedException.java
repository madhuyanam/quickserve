package com.alpha.quickserve.exception;

public class PaymentFailedException extends RuntimeException {

    public PaymentFailedException(String message){
        super(message);
    }
}
