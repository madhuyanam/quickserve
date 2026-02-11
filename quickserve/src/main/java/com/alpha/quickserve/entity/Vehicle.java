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
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", model=" + model + ", number=" + number + "]";
	}
	

}
