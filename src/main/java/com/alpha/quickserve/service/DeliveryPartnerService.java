package com.alpha.quickserve.service;


import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.alpha.quickserve.dto.DelivaryPartnerDto;
import com.alpha.quickserve.entity.DeliveryPartner;
import com.alpha.quickserve.entity.Order;
import com.alpha.quickserve.exception.DeliveryPartnerLocationNotFoundException;
import com.alpha.quickserve.exception.DeliveryPartnerNotFoundException;
import com.alpha.quickserve.exception.InvalidOrderStateException;
import com.alpha.quickserve.exception.InvalidOtpException;
import com.alpha.quickserve.exception.OrderNotFoundException;
import com.alpha.quickserve.repository.DelivaryPartnerRepository;
import com.alpha.quickserve.repository.OrderRepository;
import com.alpha.quickserve.responcestructure.ResponceStructure;
import jakarta.servlet.http.HttpServletResponse;

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

	public ResponseEntity<ResponceStructure<DeliveryPartner>> register(DelivaryPartnerDto ddto) {

		DeliveryPartner dp = new DeliveryPartner();

		dp.setName(ddto.getName());
		dp.setMob(ddto.getMob());
		dp.setMail(ddto.getMail());
		dp.setVehicileno(ddto.getVechileno());

		// optional default values
		dp.setStatus("AVAILABLE");
		dp.setRating(0);

		DeliveryPartner saved = deliveryPartnerRepo.save(dp);

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
	public ResponseEntity<ResponceStructure<String>> acceptOrder(int orderid,int partnerid){

	    Order order = orderRepo.findById(orderid)
	            .orElseThrow(() -> new OrderNotFoundException("Order not found"));

	    if(!order.getStatus().equals("ORDER_PLACED")){
	        throw new InvalidOrderStateException("Restaurant has not accepted order");
	    }

	    DeliveryPartner partner = deliveryPartnerRepo.findById(partnerid)
	            .orElseThrow(() -> new DeliveryPartnerNotFoundException("Partner not found"));

	    order.setDeliveryPartner(partner);

	    order.setStatus("ORDER_PREPARING");

	    orderRepo.save(order);

	    ResponceStructure<String> rs = new ResponceStructure<>();

	    rs.setStatusCode(200);
	    rs.setMessage("Delivery Partner Assigned");
	    rs.setData("ORDER_PREPARING");

	    return ResponseEntity.ok(rs);
	}

	//get direction from delivery partner to restaurant
	public void getDirectionToRestaurant(Integer partnerId,
			double restlat,
			double restlong,
			HttpServletResponse resp) throws IOException {

		String key = "deliverypartner:location";

		List<Point> points =
				redisTemplate.opsForGeo()
				.position(key, partnerId.toString());

		if(points == null || points.isEmpty()){
			throw new DeliveryPartnerLocationNotFoundException(
					"Delivery Partner Location not found");
		}

		Point p = points.get(0);

		double dplon = p.getX();
		double dplat = p.getY();

		String getdir =
				"https://www.google.com/maps/dir/?api=1&origin="
						+ dplat + "," + dplon
						+ "&destination=" + restlat + "," + restlong
						+ "&travelmode=driving";

		resp.sendRedirect(getdir);
	}

	//get direction from restaurant to customer
	public void getDirectionToCustomer(double restlat, double restlon, double custlat, double custlong, HttpServletResponse response) throws IOException {

		String getdir="https://www.google.com/maps/dir/?api=1&origin="+restlat+","+restlon+"&destination="+custlat+
				","+custlong+"&travelmode=driving";
		response.sendRedirect(getdir);
	}


	public ResponseEntity<ResponceStructure<String>> pickupOrder(int orderid){

		Order order = orderRepo.findById(orderid)
				.orElseThrow(() -> new OrderNotFoundException("Order not found"));

		if(!order.getStatus().equals("ORDER_PREPARING")){
			throw new InvalidOrderStateException("Order not ready for pickup");
		}

		order.setStatus("ORDER_ON_THE_WAY");

		orderRepo.save(order);

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(200);
		rs.setMessage("Order Picked Successfully");
		rs.setData("ORDER_ON_THE_WAY");

		return ResponseEntity.ok(rs);
	}


	public ResponseEntity<ResponceStructure<String>> deliverOrder(int orderid,int otp){

		Order order = orderRepo.findById(orderid)
				.orElseThrow(() -> new OrderNotFoundException("Order not found"));

		if(order.getOtp()!=otp){
			throw new InvalidOtpException("Invalid OTP");
		}

		if(!order.getStatus().equals("ORDER_ON_THE_WAY")){
			throw new InvalidOrderStateException("Order cannot be delivered now");
		}

		order.setStatus("ORDER_DELIVERED");

		orderRepo.save(order);

		ResponceStructure<String> rs = new ResponceStructure<>();

		rs.setStatusCode(200);
		rs.setMessage("Order Delivered Successfully");
		rs.setData("ORDER_DELIVERED");

		return ResponseEntity.ok(rs);
	}


}