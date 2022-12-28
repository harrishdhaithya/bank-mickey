package com.model;

import org.apache.commons.codec.digest.DigestUtils;

public class Admin extends Login{
	private long empid;
	private String name;
	private String phone;
	private String email;
	public Admin(String name, String phone, String email, String password) {
		super(email, getHash(password), "admin");
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	public Admin(long empid, String name, String phone, String email) {
		super();
		this.empid = empid;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	private static String getHash(String password) {
		String hash = DigestUtils.sha256Hex(password);
		return hash;
	}
	public long getEmpid() {
		return empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Employee Id: "+empid+"\n"+
				"Name: "+name+"\n"+
				"Phone: "+phone+"\n"+
				"Email: "+email
				; 
	}
	
}
