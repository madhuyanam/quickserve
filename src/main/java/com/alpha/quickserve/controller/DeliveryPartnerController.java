package com.alpha.quickserve.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.quickserve.dto.DelivaryPartnerDto;
import com.alpha.quickserve.entity.DeliveryPartner;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.alpha.quickserve.service.DeliveryPartnerService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {

	@Autowired
	private DeliveryPartnerService deliveryPartnerService;

	// Register
	@PostMapping("/register")
	public ResponseEntity<ResponceStructure<DeliveryPartner>> register(
			@RequestBody DelivaryPartnerDto ddto){

		return deliveryPartnerService.register(ddto);
	}

	// Find
	@GetMapping("/find")
	public ResponseEntity<ResponceStructure<DeliveryPartner>> find(
			@RequestParam long mob){

		return deliveryPartnerService.find(mob);
	}

	// Delete
	@DeleteMapping("/delete")
	public ResponseEntity<ResponceStructure<String>> delete(
			@RequestParam long mob){

		return deliveryPartnerService.delete(mob);
	}

	// Update Location
	@PostMapping("/updatelocation")
	public ResponseEntity<ResponceStructure<String>> updateLocation(
			@RequestParam Integer partnerid,
			@RequestParam double latitude,
			@RequestParam double longitude){

		return deliveryPartnerService.updateDeliveryPartnerLocation(
				partnerid, latitude, longitude);
	}

	// Accept Order
	@PostMapping("/acceptOrder")
	public ResponseEntity<ResponceStructure<String>> acceptOrder(
			@RequestParam Integer orderid,
			@RequestParam Integer partnerid){

		return deliveryPartnerService.acceptOrder(orderid,partnerid);
	}
	
	// get direction from delivery partner to restaurant
	 @GetMapping("/getDirectionToRestaurant")
	    public void getDirectionToRestaurant(@RequestParam Integer partnerId,
	                                   @RequestParam double restlat, @RequestParam double restlong,
	                                   HttpServletResponse response) throws IOException {
	         deliveryPartnerService.getDirectionToRestaurant(partnerId,restlat,restlong,response);
	    }
	 
	 //get direction from restaurant to customer
	 @GetMapping("/getDirectionToCustomer")
	    public void getDirectionToCustomer(@RequestParam double restlat,@RequestParam double restlon,@RequestParam double custlat
	                                   ,@RequestParam double custlong,HttpServletResponse response) throws IOException {
	         deliveryPartnerService.getDirectionToCustomer(restlat,restlon,custlat,custlong,response);
	    }
	 
	 @PostMapping("/pickuporder")
	    public ResponseEntity<ResponceStructure<String>> pickupOrder(
	            @RequestParam int orderid){

	        return deliveryPartnerService.pickupOrder(orderid);
	    }

	 
	 @PostMapping("/deliverorder")
	    public ResponseEntity<ResponceStructure<String>> deliverOrder(
	            @RequestParam int orderid,
	            @RequestParam int otp){

	        return deliveryPartnerService.deliverOrder(orderid,otp);
	    }
	
}