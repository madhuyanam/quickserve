package com.alpha.quickserve.Servicee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.quickserve.DTO.RestaurantDTO;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.entity.Restaurant;
import com.alpha.quickserve.repository.RestaurantRepo;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepo restaurantrepo;
	public ResponseEntity<ResponceStructure<Restaurant>> save(@RequestBody RestaurantDTO rdto) {
		Restaurant r=new Restaurant();

		r.setName(rdto.getName());
		r.setMobno(rdto.getMob());
		r.setMail(rdto.getMail());
		r.setDescription(rdto.getDescription());
		r.setPackagingFee(rdto.getPackagingFee());
		r.setType(rdto.getType());
		r.setStatus("Closed");
		Restaurant saved = restaurantrepo.save(r);

        ResponceStructure<Restaurant> response = new ResponceStructure<>();
        response.setStatusCode(200);
        response.setMessage("Restaurant saved successfully");
        response.setData(saved);

        return ResponseEntity.ok(response);
	}
	
}
