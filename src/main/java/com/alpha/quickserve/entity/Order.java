 package com.alpha.quickserve.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	
	private String status;
	private int cost;
	private int otp;

	@ManyToOne
	@JoinColumn(name = "delivery_partner_id")
	private DeliveryPartner deliveryPartner;

	private String pickupaddress;
	private String deliveryAddress;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	private Payment payment;
	
	private String estimatedTime;
	private int distance;
	private int discount;
	private String coupons;
	private String specialRequest;
	private String deliveryInstructions;
	private String date;

	@ManyToMany
	@JoinTable(
			name = "order_item",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id")
			)
	private List<Item> items;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}


	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}


	public void setDeliveryPartner(DeliveryPartner delivaryPartner) {
		this.deliveryPartner = delivaryPartner;
	}


	public String getPickupaddress() {
		return pickupaddress;
	}


	public void setPickupaddress(String pickupaddress) {
		this.pickupaddress = pickupaddress;
	}


	public String getDelivaryAddress() {
		return deliveryAddress;
	}


	public void setDelivaryAddress(String delivaryAddress) {
		this.deliveryAddress = delivaryAddress;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public String getEstimatedTime() {
		return estimatedTime;
	}


	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public String getCoupones() {
		return coupons;
	}


	public void setCoupones(String coupones) {
		this.coupons = coupones;
	}


	public String getSpecialRequest() {
		return specialRequest;
	}


	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}


	public String getDelivaryInstructions() {
		return deliveryInstructions;
	}


	public void setDelivaryInstructions(String delivaryInstructions) {
		this.deliveryInstructions = delivaryInstructions;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public List<Item> getItem() {
		return items;
	}


	public void setItem(List<Item> item) {
		this.items = item;
	}


	public Order(int id, String status, int cost, int otp, DeliveryPartner delivaryPartner, String pickupaddress,
			String delivaryAddress, Restaurant restaurant, Customer customer, Payment payment, String estimatedTime,
			int distance, int discount, String coupones, String specialRequest, String delivaryInstructions,
			String date, List<Item> item) {
		super();
		this.id = id;
		this.status = status;
		this.cost = cost;
		this.otp = otp;
		this.deliveryPartner = delivaryPartner;
		this.pickupaddress = pickupaddress;
		this.deliveryAddress = delivaryAddress;
		this.restaurant = restaurant;
		this.customer = customer;
		this.payment = payment;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.coupons = coupones;
		this.specialRequest = specialRequest;
		this.deliveryInstructions = delivaryInstructions;
		this.date = date;
		this.items = item;
	}


	public Order() {
		super();
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", cost=" + cost + ", otp=" + otp + ", delivaryPartner="
				+ deliveryPartner + ", pickupaddress=" + pickupaddress + ", delivaryAddress=" + deliveryAddress
				+ ", restaurant=" + restaurant + ", customer=" + customer + ", payment=" + payment + ", estimatedTime="
				+ estimatedTime + ", distance=" + distance + ", discount=" + discount + ", coupones=" + coupons
				+ ", specialRequest=" + specialRequest + ", delivaryInstructions=" + deliveryInstructions + ", date="
				+ date + ", item=" + items + "]";
	}




}
