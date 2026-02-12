package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
	@Id
	private int id;
	private String name;
	private String model;
	private int number;
	
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Vehicle(int id, String name, String model, int number) {
		super();
		this.id = id;
		this.name = name;
		this.model = model;
		this.number = number;
	}

	public Vehicle() {
		super();
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", model=" + model + ", number=" + number + "]";
	}
	

}
