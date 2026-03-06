package com.alpha.quickserve.exception;

public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(String msg){
        super(msg);
    }
}