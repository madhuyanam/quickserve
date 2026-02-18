package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
 private int id;
private double amount;
private String type;
private String status;

@OneToOne
@JoinColumn(name = "order_id")
private Order order;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Order getOrder() {
	return order;
}

public void setOrder(Order order) {
	this.order = order;
}

public Payment(int id, double amount, String type, String status, Order order) {
	super();
	this.id = id;
	this.amount = amount;
	this.type = type;
	this.status = status;
	this.order = order;
}

public Payment() {
	super();
}

@Override
public String toString() {
	return "Payment [id=" + id + ", amount=" + amount + ", type=" + type + ", status=" + status + ", order=" + order
			+ "]";
}




}
