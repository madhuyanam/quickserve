package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Restaurant {
	@Id
private int id;
	private String name;
	private String mail;
	private long mobno;
	private String address;
	private String status;
	private double ratings;
	private String  description;
	private int packagingFee;
	private String type;
	List<Item>item;
	List<Order>order;
	
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getMobno() {
		return mobno;
	}

	public void setMobno(long mobno) {
		this.mobno = mobno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getRatings() {
		return ratings;
	}

	public void setRatings(double ratings) {
		this.ratings = ratings;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPackagingFee() {
		return packagingFee;
	}

	public void setPackagingFee(int packagingFee) {
		this.packagingFee = packagingFee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Restaurant(int id, String name, String mail, long mobno, String address, String status, double ratings,
			String description, int packagingFee, String type, List<Item> item, List<Order> order) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.mobno = mobno;
		this.address = address;
		this.status = status;
		this.ratings = ratings;
		this.description = description;
		this.packagingFee = packagingFee;
		this.type = type;
		this.item = item;
		this.order = order;
	}

	public Restaurant() {
		super();
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", mail=" + mail + ", mobno=" + mobno + ", address="
				+ address + ", status=" + status + ", ratings=" + ratings + ", description=" + description
				+ ", packagingFee=" + packagingFee + ", type=" + type + ", item=" + item + ", order=" + order + "]";
	}
	
}
