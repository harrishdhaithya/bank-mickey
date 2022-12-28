package com.model;

import org.apache.commons.codec.digest.DigestUtils;

public class User extends Login {
	private long accno;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private double balance;
	public User(String fname, String lname, String phone, String email, String password, double balance) {
		super(email,getHash(password),"user");
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.balance = balance;
	}
	
	public User(double accno, String fname, String lname, String phone, String email, String password, double balance) {
		super(email,password,"user");
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.balance = balance;
	}
	
	public User(long accno, String fname, String lname, String phone, String email, double balance) {
		super();
		this.accno = accno;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.balance = balance;
		
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getAccno() {
		return accno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	private static String getHash(String password) {
		String hash = DigestUtils.sha256Hex(password);
		return hash;
	}
	@Override
	public String toString() {
		return "Account Number: "+accno+"\n"+
				"Name: "+fname+" "+lname+"\n"+
				"Email: "+email+"\n"+
				"Phone Number: "+phone+"\n"+
				"Balance: "+balance
				;
	}
}
