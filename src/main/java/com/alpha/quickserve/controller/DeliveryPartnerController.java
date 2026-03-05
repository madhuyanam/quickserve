package com.alpha.quickserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.quickserve.entity.DeliveryPartner;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import com.alpha.quickserve.service.DeliveryPartnerService;

@RestController
@RequestMapping("/deliverypartner")
public class DeliveryPartnerController {

	@Autowired
	private DeliveryPartnerService deliveryPartnerService;

	// Register
	@PostMapping("/register")
	public ResponseEntity<ResponceStructure<DeliveryPartner>> register(
			@RequestBody DeliveryPartner partner){

		return deliveryPartnerService.register(partner);
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
	@PostMapping("/acceptorder")
	public ResponseEntity<ResponceStructure<String>> acceptOrder(
			@RequestParam Integer orderid,
			@RequestParam Integer partnerid){

		return deliveryPartnerService.acceptOrder(orderid,partnerid);
	}
	
	// get direction 
	
}