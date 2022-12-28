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
import com.model.AdminSecret;

public class AdminSecretDao {
    public boolean saveSecret(AdminSecret a){
            long empid = a.getEmpid();
            String secret = a.getSecret();
            Row row = new Row("AdminSecret");
            row.set("EMPID", empid);
            row.set("SECRET",secret);
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
    public String getSecret(long empid){
        Criteria c = new Criteria(new Column("AdminSecret", "EMPID"), empid, QueryConstants.EQUAL);
        try{
            DataObject dObj = DataAccess.get("AdminSecret", c);
            Iterator<?> it = dObj.getRows("AdminSecret");
            if(it.hasNext()){
                Row row = (Row)it.next();
                String secret = row.getString("SECRET");
                return secret;
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return null;
    }
}