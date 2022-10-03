package com.model;

public class UserSecret {
    private long accno;
    private String secret;
    public UserSecret(long accno,String secret){
        this.accno = accno;
        this.secret = secret;
    }
    public void setAccno(long accno){
        this.accno=accno;
    }
    public void setSecret(String secret){
        this.secret=secret;
    }
    public long getAccno(){
        return accno;
    }
    public String getSecret(){
        return secret;
    }
}
