package com.auth;

import java.security.Principal;

public class UserPrincipal implements Principal {
    String name;
    public UserPrincipal(String name){
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }
    
}
