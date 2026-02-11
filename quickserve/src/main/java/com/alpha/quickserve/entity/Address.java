package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String city;
	private String state;
	private String country;
	private String street;
	private String landmark;
	private int  pincode;
	@Override
	public String toString() {
		return "Address [id=" + id + ", city=" + city + ", state=" + state + ", country=" + country + ", street="
				+ street + ", landmark=" + landmark + ", pincode=" + pincode + "]";
	}
	
	

}
