package com.alpha.quickserve.Controller1;

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
import com.alpha.quickserve.entity.Item;
import com.alpha.quickserve.entity.Restaurant;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantservice;
	
	@PostMapping("/register")
	public ResponseEntity<ResponceStructure<Restaurant>> saveRestaurant(@RequestBody RestaurantDTO rdto){

	    return restaurantservice.save(rdto);

		
	}
	
	
	 @GetMapping("/findrestaurant")
	    public ResponseEntity<ResponceStructure<Restaurant>> findrestaurant(
	            @RequestParam long mobno) {
	        return restaurantservice.findrestaurant(mobno);
	    }

	 @DeleteMapping("/deleterestaurant")
	    public ResponseEntity<ResponceStructure<Restaurant>> deleteRestaurant(
	            @RequestParam long mobno) {
	        return restaurantservice.deleteRestaurant(mobno);
	    }

	    @PatchMapping("/additemtomenu")
	    public ResponseEntity<ResponceStructure<Restaurant>> addItemToMenu(
	            @RequestParam long restaurantmobno,
	            @RequestBody Item item) {
	        return restaurantservice.addItemToMenu(restaurantmobno, item);
	    }


}
