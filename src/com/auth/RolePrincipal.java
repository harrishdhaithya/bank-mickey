package com.auth;

import java.security.Principal;



public class RolePrincipal implements Principal {
    String name;
    public RolePrincipal(String name){
        super();
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
