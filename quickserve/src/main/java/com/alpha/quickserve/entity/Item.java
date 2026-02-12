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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNumberOfServices() {
		return numberOfServices;
	}

	public void setNumberOfServices(int numberOfServices) {
		this.numberOfServices = numberOfServices;
	}

	public Item(int id, String name, String description, int price, int count, String type, String availability,
			int rating, String image, int numberOfServices) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.count = count;
		this.type = type;
		this.availability = availability;
		this.rating = rating;
		this.image = image;
		this.numberOfServices = numberOfServices;
	}

	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", count="
				+ count + ", type=" + type + ", availability=" + availability + ", rating=" + rating + ", image="
				+ image + ", numberOfServices=" + numberOfServices + "]";
	}
	

}
