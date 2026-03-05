package com.alpha.quickserve.exception;

public class RestaurantNotFoundException extends RuntimeException{
	public RestaurantNotFoundException(String message) {
        super(message);
    }

}
