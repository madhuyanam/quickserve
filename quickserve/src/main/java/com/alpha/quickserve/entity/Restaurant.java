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
	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", mail=" + mail + ", mobno=" + mobno + ", address="
				+ address + ", status=" + status + ", ratings=" + ratings + ", description=" + description
				+ ", packagingFee=" + packagingFee + ", type=" + type + ", item=" + item + ", order=" + order + "]";
	}
	
}
