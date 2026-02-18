package com.alpha.quickserve.DTO;

public class CustomerDto {
	
	private String name;
	private long mobno;
	private String mailid;
	private String gender;
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
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public CustomerDto(String name, long mobno, String mailid, String gender) {
		super();
		this.name = name;
		this.mobno = mobno;
		this.mailid = mailid;
		this.gender = gender;
	}
	public CustomerDto() {
		super();
	}
	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", mobno=" + mobno + ", mailid=" + mailid + ", gender=" + gender + "]";
	}
	
	
	

}
