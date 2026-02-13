package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true)
	private long  mobno;
	@Column(unique = true)
	private String mailid;
	private String gender;
	@OneToMany
	private Address address;
	
	List<Order> order;
	List<Item>item;
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
	public long getMobno() {
		return mobno;
	}
	public void setMobno(long mobno) {
		this.mobno = mobno;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	public Customer(int id, String name, long mobno, String mailid, String gender, Address address, List<Order> order,
			List<Item> item) {
		super();
		this.id = id;
		this.name = name;
		this.mobno = mobno;
		this.mailid = mailid;
		this.gender = gender;
		this.address = address;
		this.order = order;
		this.item = item;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobno=" + mobno + ", mailid=" + mailid + ", gender="
				+ gender + ", address=" + address + ", order=" + order + ", item=" + item + "]";
	}
	
	
	
	
	
}
