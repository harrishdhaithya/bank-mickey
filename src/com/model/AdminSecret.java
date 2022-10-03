package com.model;

public class AdminSecret {
    private long empid;
    private String secret;
    public AdminSecret(long empid,String secret){
        this.empid = empid;
        this.secret = secret;
    }
    public void setEmpid(long empid){
        this.empid=empid;
    }
    public void setSecret(String secret){
        this.secret=secret;
    }
    public long getEmpid(){
        return empid;
    }
    public String getSecret(){
        return secret;
    }
}