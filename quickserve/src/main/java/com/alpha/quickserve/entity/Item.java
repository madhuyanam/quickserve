package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {
	@Id
	private int id;
	private String name;
	private String description;
	private int price;
	private int count;
	private String type;
	private String availability;
	private int  rating;
	private String image;
	private int numberOfServices;
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", count="
				+ count + ", type=" + type + ", availability=" + availability + ", rating=" + rating + ", image="
				+ image + ", numberOfServices=" + numberOfServices + "]";
	}
	

}
