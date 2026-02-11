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
