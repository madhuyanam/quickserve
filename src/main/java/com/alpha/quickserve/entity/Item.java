package com.alpha.quickserve.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private Integer price;
	private Integer unit;
	private String type;
	private String availability;
	private Integer  rating;
	private String image;
	private Integer numberOfServices;
	
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    
    @ManyToMany
    @JoinTable(
        name = "customer_item",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
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

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getNumberOfServices() {
		return numberOfServices;
	}

	public void setNumberOfServices(Integer numberOfServices) {
		this.numberOfServices = numberOfServices;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Item(int id, String name, String description, Integer price, Integer unit, String type, String availability,
			Integer rating, String image, Integer numberOfServices, Restaurant restaurant, List<Customer> customers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.unit = unit;
		this.type = type;
		this.availability = availability;
		this.rating = rating;
		this.image = image;
		this.numberOfServices = numberOfServices;
		this.restaurant = restaurant;
		this.customers = customers;
	}

	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", unit="
				+ unit + ", type=" + type + ", availability=" + availability + ", rating=" + rating + ", image=" + image
				+ ", numberOfServices=" + numberOfServices + ", restaurant=" + restaurant + ", customers=" + customers
				+ "]";
	}

    
	

}
