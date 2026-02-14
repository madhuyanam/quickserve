package com.alpha.quickserve.Controller1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.quickserve.DTO.RestaurantDTO;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.Servicee.RestaurantService;
import com.alpha.quickserve.entity.DelivaryPartner;
import com.alpha.quickserve.entity.Restaurant;

@RestController
@RequestMapping("/restuarant")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantservice;
	@PostMapping("/register")
	public ResponseEntity<ResponceStructure<Restaurant>> saveRestaurant(@RequestBody RestaurantDTO rdto){

	    return restaurantservice.save(rdto);

		
	}
}
