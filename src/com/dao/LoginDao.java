package com.dao;

import java.util.Iterator;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.model.Login;

public class LoginDao {
    public Login getLoginByEmail(String email){
        Login login = null;
        Criteria criteria = new Criteria(new Column("Login", "EMAIL"), email, QueryConstants.EQUAL);
        try{
            DataObject dobj = DataAccess.get("Login", criteria);
            Iterator<?> it = dobj.getRows("Login");
            while(it.hasNext()){
                Row row = (Row)it.next();
                String password = row.getString("PASSWORD");
                String role = row.getString("ROLE");
                login = new Login(email, password, role);
            }
        }catch(DataAccessException dex){
            dex.printStackTrace();
        }
        return login;
    }
    public boolean addLogin(Login login){
        String email = login.getEmail();
        String passwordHash = login.getPasswordHash();
        String role = login.getRole();
        Row row = new Row("Login");
        row.set("EMAIL", email);
        row.set("PASSWORD", passwordHash);
        row.set("ROLE", role);
        DataObject dObj = new WritableDataObject();
        try{
            dObj.addRow(row);
            DataAccess.add(dObj);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
