package com.alpha.quickserve.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(unique = true)
	private String mail;
	
	@Column(unique = true)
	private long mobno;

	private String status;
	private double ratings;
	private String  description;
	private int packagingFee;
	private String type;

	@OneToMany(mappedBy="restaurant",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> menuItems;

	@OneToMany(mappedBy="restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

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

	public List<Item> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<Item> menuItems) {
		this.menuItems = menuItems;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Restaurant(int id, String name, String mail, long mobno, String status, double ratings, String description,
			int packagingFee, String type, List<Item> menuItems, List<Order> orders, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.mobno = mobno;
		this.status = status;
		this.ratings = ratings;
		this.description = description;
		this.packagingFee = packagingFee;
		this.type = type;
		this.menuItems = menuItems;
		this.orders = orders;
		this.address = address;
	}

	public Restaurant() {
		super();
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", mail=" + mail + ", mobno=" + mobno + ", status=" + status
				+ ", ratings=" + ratings + ", description=" + description + ", packagingFee=" + packagingFee + ", type="
				+ type + ", menuItems=" + menuItems + ", orders=" + orders + ", address=" + address + "]";
	}



}
