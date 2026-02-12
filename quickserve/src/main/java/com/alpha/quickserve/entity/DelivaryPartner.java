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
	
	
	
	
	
	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public long getMob() {
		return mob;
	}





	public void setMob(long mob) {
		this.mob = mob;
	}





	public String getMail() {
		return mail;
	}





	public void setMail(String mail) {
		this.mail = mail;
	}





	public double getRating() {
		return rating;
	}





	public void setRating(double rating) {
		this.rating = rating;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String getDisabled() {
		return disabled;
	}





	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}





	public Address getAddress() {
		return address;
	}





	public void setAddress(Address address) {
		this.address = address;
	}





	public List<Order> getOrder() {
		return order;
	}





	public void setOrder(List<Order> order) {
		this.order = order;
	}





	public DelivaryPartner(int id, String name, long mob, String mail, double rating, String status, String disabled,
			Address address, List<Order> order) {
		super();
		this.id = id;
		this.name = name;
		this.mob = mob;
		this.mail = mail;
		this.rating = rating;
		this.status = status;
		this.disabled = disabled;
		this.address = address;
		this.order = order;
	}





	public DelivaryPartner() {
		super();
	}





	@Override
	public String toString() {
		return "DelivaryPartner [id=" + id + ", name=" + name + ", mob=" + mob + ", mail=" + mail + ", rating=" + rating
				+ ", status=" + status + ", disabled=" + disabled + ", address=" + address + ", order=" + order + "]";
	}
	
	
}
