package com.alpha.quickserve.DTO;

public class DelivaryPartnerDTO {
	private String name;
	private long mob;
	private String mail;
	private String vehicileno;
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
	public String getVehicileno() {
		return vehicileno;
	}
	public void setVehicileno(String vehicileno) {
		this.vehicileno = vehicileno;
	}
	public DelivaryPartnerDTO(String name, long mob, String mail, String vehicileno) {
		super();
		this.name = name;
		this.mob = mob;
		this.mail = mail;
		this.vehicileno = vehicileno;
	}
	public DelivaryPartnerDTO() {
		super();
	}
	@Override
	public String toString() {
		return "DeliveryPartnerDTO [name=" + name + ", mob=" + mob + ", mail=" + mail + ", vehicileno=" + vehicileno
				+ "]";
	}
	
	

}
