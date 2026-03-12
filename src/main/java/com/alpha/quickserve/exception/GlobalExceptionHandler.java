package com.alpha.quickserve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.quickserve.responcestructure.ResponceStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Customer Not Found
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleCustomerNotFound(
			CustomerNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Customer Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
	}

	// Restaurant Not Found
	@ExceptionHandler(RestaurantNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleRestaurantNotFound(
			RestaurantNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Restaurant Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
	}

	// Item Not Found
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleItemNotFound(
			ItemNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Item Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
	}

	// Order Not Found
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleOrderNotFound(
			OrderNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Order Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
	}

	// Cart Empty
	@ExceptionHandler(CartEmptyException.class)
	public ResponseEntity<ResponceStructure<String>> handleCartEmpty(
			CartEmptyException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Cart Empty");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.BAD_REQUEST);
	}

	// Delivery Partner Not Found
	@ExceptionHandler(DeliveryPartnerNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleDeliveryPartnerNotFound(
			DeliveryPartnerNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Delivery Partner Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
	}

	// Delivery Partner Location Not Found
	@ExceptionHandler(DeliveryPartnerLocationNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleLocationNotFound(
			DeliveryPartnerLocationNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Delivery Partner Location Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
	}

	// Coupon Not Found
	@ExceptionHandler(CouponNotFoundException.class)
	public ResponseEntity<ResponceStructure<String>> handleCouponNotFound(CouponNotFoundException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Coupon Not Found");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
	}

	// Coupon Invalid
	@ExceptionHandler(CouponInvalidException.class)
	public ResponseEntity<ResponceStructure<String>> handleCouponInvalid(CouponInvalidException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Invalid Coupon");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}

	// Coupon Expired
	@ExceptionHandler(CouponExpiredException.class)
	public ResponseEntity<ResponceStructure<String>> handleCouponExpired(CouponExpiredException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Coupon Expired");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}

	// Coupon Limit Exceeded
	@ExceptionHandler(CouponLimitExceededException.class)
	public ResponseEntity<ResponceStructure<String>> handleCouponLimitExceeded(CouponLimitExceededException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Coupon Limit Reached");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}
	// Invalid Otp
	@ExceptionHandler(InvalidOtpException.class)
	public ResponseEntity<ResponceStructure<String>> handleInvalidOtpException(InvalidOtpException ex){

		ResponceStructure<String> rs = new ResponceStructure<>();
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Invalid Otp");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs, HttpStatus.BAD_REQUEST);
	}


	// Generic Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponceStructure<String>> handleGenericException(
			Exception ex){

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		rs.setMessage("Something Went Wrong");
		rs.setData(ex.getMessage());

		return new ResponseEntity<>(rs,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}