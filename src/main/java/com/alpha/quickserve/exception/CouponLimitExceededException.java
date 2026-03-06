package com.alpha.quickserve.exception;

public class CouponLimitExceededException extends RuntimeException{
    public CouponLimitExceededException (String msg){
        super(msg);
    }   
}


