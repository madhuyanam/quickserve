package com.alpha.quickserve.Controller1;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.quickserve.DTO.RestaurantDTO;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.Servicee.RestaurantService;
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
	
	@GetMapping("/findrestaurant/{phoneno}")
	public ResponseEntity<ResponceStructure<Restaurant>> findrestaurant(@RequestParam long mobno){
		return restaurantservice.findrestaurant(mobno);
		
	}

	@DeleteMapping("/deletecustomer/{phoneno}")
	public ResponseEntity<ResponceStructure<Restaurant>> deleteCustomer(@RequestParam long mobno){
		return restaurantservice.deleteCustomer(mobno);
		
	}
	 @PatchMapping("/updatestatus")
	    public ResponseEntity<String> updateStatus(@RequestParam("restaurantMobno") Long mobno,
	    		                                   @RequestBody Map<String, String> request) {

	        String status = request.get("status");
	        restaurantservice.updateStatusByMobNo(mobno, status);

	        return ResponseEntity.ok("Restaurant status updated successfully");
	    }
	

}
