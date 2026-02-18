package com.alpha.quickserve.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.quickserve.ResponceStructure.ResponceStructure;
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DeliveryPartnerNotFoundException.class)
    public ResponseEntity<ResponceStructure<String>> handleDPNotFound(
            DeliveryPartnerNotFoundException ex) {

        ResponceStructure<String> response = new ResponceStructure<>();

        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage("Delivery Partner Not Found");
        response.setData(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponceStructure<String>> handleGeneric(Exception ex) {

        ResponceStructure<String> response = new ResponceStructure<>();

        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Something Went Wrong");
        response.setData(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
