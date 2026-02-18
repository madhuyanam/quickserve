package com.alpha.quickserve.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private Double latitude;
	private Double longitude;
	
	//HOUSE details
	private String flatNumber;
	private String floor;
	private String buildingName;
	private String street;
	
	//Area Deatils
	private String area;
	private String landmark;
	private String city;
	private String district;
	private String state;
	private String country;
	private int  pincode;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public Address(int id, Double latitude, Double longitude, String flatNumber, String floor, String buildingName,
			String street, String area, String landmark, String city, String district, String state, String country,
			int pincode) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.flatNumber = flatNumber;
		this.floor = floor;
		this.buildingName = buildingName;
		this.street = street;
		this.area = area;
		this.landmark = landmark;
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	public Address() {
		super();
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", flatNumber="
				+ flatNumber + ", floor=" + floor + ", buildingName=" + buildingName + ", street=" + street + ", area="
				+ area + ", landmark=" + landmark + ", city=" + city + ", district=" + district + ", state=" + state
				+ ", country=" + country + ", pincode=" + pincode + "]";
	}
	public void setPincode(Object object) {
		// TODO Auto-generated method stub
		
	}
	public void setCity(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	

}
