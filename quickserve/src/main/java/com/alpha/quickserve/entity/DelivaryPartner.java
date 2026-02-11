package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DelivaryPartner {
	@Id
	private int id;
	private String name;
	private long mob;
	private String mail;
	private double  rating;
	private String status ;
	private String disabled;
	private  Address address ;
	List <Order> order;
	@Override
	public String toString() {
		return "DelivaryPartner [id=" + id + ", name=" + name + ", mob=" + mob + ", mail=" + mail + ", rating=" + rating
				+ ", status=" + status + ", disabled=" + disabled + ", address=" + address + ", order=" + order + "]";
	}
	
	
}
