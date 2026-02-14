package com.alpha.quickserve.DTO;

public class RestaurantDTO {
	private String name;
	private long mob;
	private String mail;
	private String description;
	private int packagingFee;
	private String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
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
	public RestaurantDTO(String name, long mob, String mail, String description, int packagingFee, String type) {
		super();
		this.name = name;
		this.mob = mob;
		this.mail = mail;
		this.description = description;
		this.packagingFee = packagingFee;
		this.type = type;
	}
	public RestaurantDTO() {
		super();
	}
	@Override
	public String toString() {
		return "RestaurantDTO [name=" + name + ", mob=" + mob + ", mail=" + mail + ", description=" + description
				+ ", packagingFee=" + packagingFee + ", type=" + type + "]";
	}
	
	
	
}
