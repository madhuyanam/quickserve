package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String name;
	private long  mobno;
	private String mailid;
	private String gender;
	private Address address;
	
	List<Order> order;
	List<Item>item;
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobno=" + mobno + ", mailid=" + mailid + ", gender="
				+ gender + ", address=" + address + ", order=" + order + ", item=" + item + "]";
	}

}
