package com.alpha.quickserve.Servicee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.alpha.quickserve.DTO.DelivaryPartnerDTO;
import com.alpha.quickserve.Exception.DeliveryPartnerNotFoundException;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.entity.DelivaryPartner;
import com.alpha.quickserve.repository.DelivaryPartnerRepo;

@Service
public class DeliveryPartnerService {
	
	@Autowired 
	private DelivaryPartnerRepo delivarypartnerrepo;
	

    public ResponceStructure<DelivaryPartner> saveDP(DelivaryPartner dp) {

        DelivaryPartner saved = delivarypartnerrepo.save(dp);

        ResponceStructure<DelivaryPartner> response = new ResponceStructure<>();

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Delivery Partner Saved Successfully");
        response.setData(saved);

        return response;
    }
    
    
    public DelivaryPartner getById(int id) {

        return delivarypartnerrepo.findById(id)
                .orElseThrow(() ->
                        new DeliveryPartnerNotFoundException("DP with ID " + id + " not found"));
    }


}
