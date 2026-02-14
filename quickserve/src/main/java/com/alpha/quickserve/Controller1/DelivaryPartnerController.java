package com.alpha.quickserve.Controller1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.quickserve.DTO.DelivaryPartnerDTO;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.Servicee.DeliveryPartnerService;
import com.alpha.quickserve.entity.DelivaryPartner;

@RestController
@RequestMapping("/delivarypartner")
public class DelivaryPartnerController {
	
	@Autowired
	private DeliveryPartnerService dpservice;
	
	@PostMapping("/register")
	public ResponseEntity<ResponceStructure<DelivaryPartner>> savedp(@RequestBody DelivaryPartnerDTO dpdto){
		DelivaryPartner dp=new DelivaryPartner();
		
		dp.setName(dpdto.getName());
		dp.setMob(dpdto.getMob());
		dp.setMail(dpdto.getMail());
		dp.setVehicileno(dpdto.getVehicileno());
		

        ResponceStructure<DelivaryPartner> response = dpservice.saveDP(dp);

        return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	

}
