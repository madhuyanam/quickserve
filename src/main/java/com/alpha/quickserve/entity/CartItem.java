package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public CartItem(int id, Item item, int quantity) {
		super();
		this.id = id;
		this.item = item;
		this.quantity = quantity;
	}
	public CartItem() {
		super();
	}
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", item=" + item + ", quantity=" + quantity + "]";
	}


}
