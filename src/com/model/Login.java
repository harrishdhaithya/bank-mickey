package com.model;



import org.apache.commons.codec.digest.DigestUtils;

public class Login {
    private String email;
    private String passwordHash;
    private String role;
    public Login(){}
    public Login(String email,String passwordHash,String role){
        this.email=email;
        this.passwordHash=passwordHash;
        this.role=role;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPassword(String passwordHash){
        this.passwordHash=passwordHash;
    }
    public void setRole(String role){
        this.role=role;
    }
    public String getEmail(){
        return email;
    }
    public String getPasswordHash(){
        return passwordHash;
    }
    public String getRole(){
        return role;
    }
    private static String getHash(String password) {
		String hash = DigestUtils.sha256Hex(password);
		return hash;
	}
    public boolean evalPassword(String password){
        return this.passwordHash.equals(getHash(password));
    }
}
