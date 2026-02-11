package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Payment {
@Id
 private int id;
private double amount;
private String type;
private String status;
private Order order;
@Override
public String toString() {
	return "Payment [id=" + id + ", amount=" + amount + ", type=" + type + ", status=" + status + ", order=" + order
			+ "]";
}




}
