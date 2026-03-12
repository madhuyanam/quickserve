package com.alpha.quickserve.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	private String name;

	@Column(unique = true)
	private Long  mobno;
	
	@Column(unique = true)
	private String mailid;

	private String gender;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(mappedBy = "customer")
	private List<Order> orders;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<CartItem> cart = new ArrayList<>();
	
	private double penalty;
    private double wallet;
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
	public Long getMobno() {
		return mobno;
	}
	public void setMobno(Long mobno) {
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<CartItem> getCart() {
		return cart;
	}
	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}
	public double getPenalty() {
		return penalty;
	}
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public Customer(int id, String name, Long mobno, String mailid, String gender, Address address, List<Order> orders,
			List<CartItem> cart, double penalty, double wallet) {
		super();
		this.id = id;
		this.name = name;
		this.mobno = mobno;
		this.mailid = mailid;
		this.gender = gender;
		this.address = address;
		this.orders = orders;
		this.cart = cart;
		this.penalty = penalty;
		this.wallet = wallet;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", mobno=" + mobno + ", mailid=" + mailid + ", gender="
				+ gender + ", address=" + address + ", orders=" + orders + ", cart=" + cart + ", penalty=" + penalty
				+ ", wallet=" + wallet + "]";
	}

	

}