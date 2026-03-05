package com.alpha.quickserve.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alpha.quickserve.entity.DeliveryPartner;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.exception.DeliveryPartnerNotFoundException;
import com.alpha.quickserve.exception.OrderNotFoundException;
import com.alpha.quickserve.repository.DelivaryPartnerRepository;
import com.alpha.quickserve.repository.OrderRepository;
import com.alpha.quickserve.responcestructure.ResponceStructure;

@Service
public class DeliveryPartnerService {

	@Autowired
	private DelivaryPartnerRepository deliveryPartnerRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private RedisService redisService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	// Register
	public ResponseEntity<ResponceStructure<DeliveryPartner>> register(DeliveryPartner partner) {

		DeliveryPartner saved = deliveryPartnerRepo.save(partner);

		ResponceStructure<DeliveryPartner> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Delivery Partner Registered Successfully");
		rs.setData(saved);

		return new ResponseEntity<>(rs, HttpStatus.CREATED);
	}

	// Find
	public ResponseEntity<ResponceStructure<DeliveryPartner>> find(long mob) {

		DeliveryPartner partner = deliveryPartnerRepo.findByMob(mob)
				.orElseThrow(() -> new DeliveryPartnerNotFoundException("Delivery Partner not found"));

		ResponceStructure<DeliveryPartner> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Delivery Partner Found");
		rs.setData(partner);

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	// Delete
	public ResponseEntity<ResponceStructure<String>> delete(long mob) {

		DeliveryPartner partner = deliveryPartnerRepo.findByMob(mob)
				.orElseThrow(() -> new DeliveryPartnerNotFoundException("Delivery Partner not found"));

		deliveryPartnerRepo.delete(partner);

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Delivery Partner Deleted");
		rs.setData("Deleted Successfully");

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	// Update Location
	public ResponseEntity<ResponceStructure<String>> updateDeliveryPartnerLocation(Integer partnerid, double latitude,
			double longitude) {

		String result = redisService.updateDpLoc(partnerid, latitude, longitude);

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage(result);
		rs.setData(result);

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	// Accept Order
	public ResponseEntity<ResponceStructure<String>> acceptOrder(Integer orderid, Integer partnerid) {

		Order order = orderRepo.findById(orderid).orElseThrow(() -> new OrderNotFoundException("Order not found"));

		DeliveryPartner partner = deliveryPartnerRepo.findById(partnerid)
				.orElseThrow(() -> new DeliveryPartnerNotFoundException("Partner not found"));

		String lockKey = "order_lock:" + orderid;

		Boolean locked = redisTemplate.opsForValue().setIfAbsent(lockKey, partnerid.toString());

		String message;

		if (Boolean.TRUE.equals(locked)) {

			order.setDeliveryPartner(partner);
			// set order to the delivery partner also
			order.setStatus("ASSIGNED");

			orderRepo.save(order);

			redisTemplate.delete("order:" + orderid);

			message = "Order assigned successfully";
		} else {
			message = "Order already taken by another partner";
		}

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Order Response");
		rs.setData(message);

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
}