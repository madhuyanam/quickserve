package com.alpha.quickserve.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class LocationCoordinates {
	private double latitude;
	private double longitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocationCoordinates(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public LocationCoordinates() {
		super();
	}
	@Override
	public String toString() {
		return "LocationCoordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	

}
