package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {
	@Id
	private int id;
	private String status;
	private int cost;
	private int otp;
	private String delivaryPartner;
	private String pickupaddress;
	private String delivaryAddress;
	private Restaurant restaurant;
	private Customer customer;
	private Payment payment;
	private String estimatedTime;
	private int distance;
	private int discount;
	private String coupones;
	private String specialRequest;
	private String delivaryInstructions;
	private String date;
	

	
	List<Item>item;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getDelivaryPartner() {
		return delivaryPartner;
	}

	public void setDelivaryPartner(String delivaryPartner) {
		this.delivaryPartner = delivaryPartner;
	}

	public String getPickupaddress() {
		return pickupaddress;
	}

	public void setPickupaddress(String pickupaddress) {
		this.pickupaddress = pickupaddress;
	}

	public String getDelivaryAddress() {
		return delivaryAddress;
	}

	public void setDelivaryAddress(String delivaryAddress) {
		this.delivaryAddress = delivaryAddress;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getCoupones() {
		return coupones;
	}

	public void setCoupones(String coupones) {
		this.coupones = coupones;
	}

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public String getDelivaryInstructions() {
		return delivaryInstructions;
	}

	public void setDelivaryInstructions(String delivaryInstructions) {
		this.delivaryInstructions = delivaryInstructions;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Order(int id, String status, int cost, int otp, String delivaryPartner, String pickupaddress,
			String delivaryAddress, Restaurant restaurant, Customer customer, Payment payment, String estimatedTime,
			int distance, int discount, String coupones, String specialRequest, String delivaryInstructions,
			String date, List<Item> item) {
		super();
		this.id = id;
		this.status = status;
		this.cost = cost;
		this.otp = otp;
		this.delivaryPartner = delivaryPartner;
		this.pickupaddress = pickupaddress;
		this.delivaryAddress = delivaryAddress;
		this.restaurant = restaurant;
		this.customer = customer;
		this.payment = payment;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.discount = discount;
		this.coupones = coupones;
		this.specialRequest = specialRequest;
		this.delivaryInstructions = delivaryInstructions;
		this.date = date;
		this.item = item;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", cost=" + cost + ", otp=" + otp + ", delivaryPartner="
				+ delivaryPartner + ", pickupaddress=" + pickupaddress + ", delivaryAddress=" + delivaryAddress
				+ ", restaurant=" + restaurant + ", customer=" + customer + ", payment=" + payment + ", estimatedTime="
				+ estimatedTime + ", distance=" + distance + ", discount=" + discount + ", coupones=" + coupones
				+ ", specialRequest=" + specialRequest + ", delivaryInstructions=" + delivaryInstructions + ", date="
				+ date + ", item=" + item + "]";
	}
	
	
	
	

}
