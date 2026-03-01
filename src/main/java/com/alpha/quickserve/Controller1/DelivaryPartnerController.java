

package com.alpha.quickserve.Controller1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alpha.quickserve.DTO.DelivaryPartnerDTO;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.Servicee.DeliveryPartnerService;
import com.alpha.quickserve.Servicee.RedisService;
import com.alpha.quickserve.entity.DelivaryPartner;

@RestController
@RequestMapping("/delivarypartner")
public class DelivaryPartnerController {

	@Autowired
	private DeliveryPartnerService dpservice;
	 @Autowired
	    private RedisService redisService;
	@PostMapping("/register")
	public ResponseEntity<ResponceStructure<DelivaryPartner>> savedp(
			@RequestBody DelivaryPartnerDTO dpdto){

		ResponceStructure<DelivaryPartner> response = dpservice.saveDP(dpdto);

		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	@DeleteMapping("/deletebymob")
	public ResponseEntity<ResponceStructure<DelivaryPartner>> deleteByMob(
			@RequestParam long mob){

		ResponceStructure<DelivaryPartner> response = dpservice.deleteByMob(mob);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@GetMapping("/findbymob")
	public ResponseEntity<ResponceStructure<DelivaryPartner>> findByMob(
			@RequestParam long mob){

		ResponceStructure<DelivaryPartner> response = dpservice.findByMob(mob);

		return ResponseEntity.status(response.getStatusCode()).body(response);
	}

	@PostMapping("/updatelocation")
	public String updateDplocation(
	        @RequestParam Integer dpid,
	        @RequestParam double latitude,
	        @RequestParam double longitude) {

	    return redisService.updateDPloc(dpid, latitude, longitude);
	}

}

