package com.alpha.quickserve.dto;

public class RestaurantDto {

	private String name;
	private long mobno;
	private String mail;
	private String description;
	private LocationCoordinates coordinates;
	private int packagingFee;
	private String type;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getMobno() {
		return mobno;
	}
	public void setMobno(long mobno) {
		this.mobno = mobno;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public LocationCoordinates getCoordinates() {
		return coordinates;
	}
	public void setCordinates(LocationCoordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getPackagingFee() {
		return packagingFee;
	}
	public void setPackagingFee(int packagingFee) {
		this.packagingFee = packagingFee;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public RestaurantDto(String name, long mobno, String mail, String description, LocationCoordinates coordinates,
			int packagingFee, String type) {
		super();
		this.name = name;
		this.mobno = mobno;
		this.mail = mail;
		this.description = description;
		this.coordinates = coordinates;
		this.packagingFee = packagingFee;
		this.type = type;
	}

	public RestaurantDto() {
		super();
	}

	@Override
	public String toString() {
		return "RestaurantDTO [name=" + name + ", mobno=" + mobno + ", mail=" + mail + ", description=" + description
				+ ", coordinates=" + coordinates + ", packagingFee=" + packagingFee + ", type=" + type + "]";
	}

}