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
	private double cost;
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
	
	
	//added after coupon logic implementation
	private double originalAmount;
	private double discountAmount;
	private double finalAmount;
	
	@ManyToOne
	@JoinColumn(name = "coupon_id")
	private Coupon coupon;

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

	public double getCost() {
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

	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public String getPickupaddress() {
		return pickupaddress;
	}

	public void setPickupaddress(String pickupaddress) {
		this.pickupaddress = pickupaddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public String getSpecialRequest() {
		return specialRequest;
	}

	public void setSpecialRequest(String specialRequest) {
		this.specialRequest = specialRequest;
	}

	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}

	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Order(int id, String status, double cost, int otp, DeliveryPartner deliveryPartner, String pickupaddress,
			String deliveryAddress, Customer customer, Restaurant restaurant, Payment payment, String estimatedTime,
			int distance, String specialRequest, String deliveryInstructions, String date,
			List<Item> items, double originalAmount, double discountAmount, double finalAmount, Coupon coupon) {
		super();
		this.id = id;
		this.status = status;
		this.cost = cost;
		this.otp = otp;
		this.deliveryPartner = deliveryPartner;
		this.pickupaddress = pickupaddress;
		this.deliveryAddress = deliveryAddress;
		this.customer = customer;
		this.restaurant = restaurant;
		this.payment = payment;
		this.estimatedTime = estimatedTime;
		this.distance = distance;
		this.specialRequest = specialRequest;
		this.deliveryInstructions = deliveryInstructions;
		this.date = date;
		this.items = items;
		this.originalAmount = originalAmount;
		this.discountAmount = discountAmount;
		this.finalAmount = finalAmount;
		this.coupon = coupon;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", cost=" + cost + ", otp=" + otp + ", deliveryPartner="
				+ deliveryPartner + ", pickupaddress=" + pickupaddress + ", deliveryAddress=" + deliveryAddress
				+ ", customer=" + customer + ", restaurant=" + restaurant + ", payment=" + payment + ", estimatedTime="
				+ estimatedTime + ", distance=" + distance + ",specialRequest=" + specialRequest + ", deliveryInstructions=" + deliveryInstructions + ", date="
				+ date + ", items=" + items + ", originalAmount=" + originalAmount + ", discountAmount="
				+ discountAmount + ", finalAmount=" + finalAmount + ", coupon=" + coupon + "]";
	}
	
	

}
