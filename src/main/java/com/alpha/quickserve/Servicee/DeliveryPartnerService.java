package com.alpha.quickserve.Servicee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.alpha.quickserve.DTO.DelivaryPartnerDTO;
import com.alpha.quickserve.Exception.DeliveryPartnerNotFoundException;
import com.alpha.quickserve.ResponceStructure.ResponceStructure;
import com.alpha.quickserve.entity.DelivaryPartner;
import com.alpha.quickserve.repository.DelivaryPartnerRepo;

@Service
public class DeliveryPartnerService {
	
	@Autowired 
	private DelivaryPartnerRepo delivarypartnerrepo;
	
	public ResponceStructure<DelivaryPartner> saveDP(DelivaryPartnerDTO dpdto) {

	    DelivaryPartner dp = new DelivaryPartner();

	    dp.setName(dpdto.getName());
	    dp.setMob(dpdto.getMob());
	    dp.setMail(dpdto.getMail());
	    dp.setVehicileno(dpdto.getVechileno());

	    DelivaryPartner saved = delivarypartnerrepo.save(dp);

	    ResponceStructure<DelivaryPartner> response = new ResponceStructure<>();

	    response.setStatusCode(HttpStatus.CREATED.value());
	    response.setMessage("Delivery Partner Saved Successfully");
	    response.setData(saved);

	    return response;
	}

	public ResponceStructure<DelivaryPartner> deleteByMob(long mob) {

	    DelivaryPartner dp = delivarypartnerrepo.findByMob(mob)
	            .orElseThrow(() ->
	                    new DeliveryPartnerNotFoundException(
	                            "Delivery Partner with Mobile " + mob + " not found"));

	    delivarypartnerrepo.delete(dp);

	    ResponceStructure<DelivaryPartner> response = new ResponceStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Delivery Partner Deleted Successfully");
	    response.setData(dp);

	    return response;
	}
	
	public ResponceStructure<DelivaryPartner> findByMob(long mob) {

	    DelivaryPartner dp = delivarypartnerrepo.findByMob(mob)
	            .orElseThrow(() ->
	                    new DeliveryPartnerNotFoundException(
	                            "Delivery Partner with Mobile " + mob + " not found"));

	    ResponceStructure<DelivaryPartner> response = new ResponceStructure<>();
	    response.setStatusCode(HttpStatus.OK.value());
	    response.setMessage("Delivery Partner Found Successfully");
	    response.setData(dp);

	    return response;
	}

	
	
    


}