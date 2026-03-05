package com.alpha.quickserve.dto;

public class DelivaryPartnerDto {
	
	private String name;
	private long mob;
	private String mail;
	private String vechileno;

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

	public String getVechileno() {
		return vechileno;
	}
	public void setVehicileno(String vechileno) {
		this.vechileno = vechileno;
	}

	public DelivaryPartnerDto(String name, long mob, String mail, String vechileno) {
		super();
		this.name = name;
		this.mob = mob;
		this.mail = mail;
		this.vechileno = vechileno;
	}

	public DelivaryPartnerDto() {
		super();
	}

	@Override
	public String toString() {
		return "DeliveryPartnerDTO [name=" + name + ", mob=" + mob + ", mail=" + mail + ", vechileno=" + vechileno
				+ "]";
	}

}