package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
private int id;
	private String name;
	@Column(unique = true)
	private String mail;
	@Column(unique = true)
	private long mobno;
	
	@Embedded
	private LocationCoordinates locationcoordinates;
	private String status;
	private double ratings;
	private String  description;
	private int packagingFee;
	private String type;
	
	@OneToMany(mappedBy = "restaurant")
	private List<Item>menu;
	
	@OneToMany(mappedBy = "restaurant")
	private List<Order>order;

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

	public LocationCoordinates getLocationcoordinates() {
		return locationcoordinates;
	}

	public void setLocationcoordinates(LocationCoordinates locationcoordinates) {
		this.locationcoordinates = locationcoordinates;
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

	public List<Item> getMenu() {
		return menu;
	}

	public void setMenu(List<Item> menu) {
		this.menu = menu;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Restaurant(int id, String name, String mail, long mobno, LocationCoordinates locationcoordinates,
			String status, double ratings, String description, int packagingFee, String type, List<Item> menu,
			List<Order> order) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
		this.mobno = mobno;
		this.locationcoordinates = locationcoordinates;
		this.status = status;
		this.ratings = ratings;
		this.description = description;
		this.packagingFee = packagingFee;
		this.type = type;
		this.menu = menu;
		this.order = order;
	}

	public Restaurant() {
		super();
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", mail=" + mail + ", mobno=" + mobno
				+ ", locationcoordinates=" + locationcoordinates + ", status=" + status + ", ratings=" + ratings
				+ ", description=" + description + ", packagingFee=" + packagingFee + ", type=" + type + ", menu="
				+ menu + ", order=" + order + "]";
	}
	
	
}
