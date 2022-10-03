package com.dao;

import java.util.*;
import javax.transaction.TransactionManager;
import com.Singleton.Singleton;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Persistence;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.model.Admin;
import com.model.AdminSecret;

public class AdminDao {
    public List<Admin> getAllAdmins(){
        List<Admin> admins = new ArrayList<>();
        try{
            DataObject dObj = DataAccess.get("Admin",(Criteria)null);
            Iterator<Row> it = dObj.getAddedRows("Admin");
            while(it.hasNext()){
                Row r = it.next();
                long empid = r.getLong("EMPID");
                String email = r.getString("EMAIL");
                String name = r.getString("NAME");
                String phone = r.getString("PHONE");
                String passwordHash = r.getString("PASSWORD");
                Admin admin = new Admin(empid, name, phone, email, passwordHash);
                admins.add(admin);
            }
        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return admins;
    }
    public Admin getAdminByEmpId(long empid){
        Admin a = null;
        Criteria c = new Criteria(new Column("Admin", "EMPID"), empid, QueryConstants.EQUAL);
        try {
            DataObject dobj = DataAccess.get("Admin", c);
            Iterator<Row> it = dobj.getAddedRows("Admin");
            if(it.hasNext()){
                Row r = it.next();
                String email = r.getString("EMAIL");
                String name = r.getString("NAME");
                String phone = r.getString("PHONE");
                String passwordHash = r.getString("PASSWORD");
                a = new Admin(empid, name, phone, email, passwordHash);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return a;
    }
    public Admin getAdminByEmail(String email){
        Admin a = null;
        Criteria c = new Criteria(new Column("Admin", "EMAIL"), email, QueryConstants.EQUAL);
        try {
            DataObject dobj = DataAccess.get("Admin", c);
            Iterator<?> it = dobj.getRows("Admin");
            if(it.hasNext()){
                Row r = (Row)it.next();
                long empid = r.getLong("EMPID");
                String name = r.getString("NAME");
                String phone = r.getString("PHONE");
                String passwordHash = r.getString("PASSWORD");
                a = new Admin(empid, name, phone, email, passwordHash);
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return a;
    }
    public boolean saveAdmin(Admin a){
        long empid = a.getEmpid();
        String name = a.getName();
        String phone = a.getPhone();
        String email = a.getEmail();
        String passwordhash = a.getPasswordHash();
        Row row = new Row("Admin");
        // row.set("EMPID", empid);
        row.set("NAME", name);
        row.set("PHONE", phone);
        row.set("EMAIL", email);
        row.set("PASSWORD", passwordhash);
        DataObject dobj = new WritableDataObject();
        try {
            Persistence per = (Persistence)BeanUtil.lookup("Persistence");
            dobj.addRow(row);
            per.add(dobj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addAdmin(Admin a, String secert){
        TransactionManager tm = DataAccess.getTransactionManager();
        try{
            tm.begin();
            {
                AdminSecretDao asdao = Singleton.getAdminSecretDao();
                boolean s1 = this.saveAdmin(a);
                boolean s2 = asdao.saveSecret(new AdminSecret(this.getAdminByEmail(a.getEmail()).getEmpid(), secert));
                boolean success = s1&&s2;
                if(success){
                    tm.commit();
                    return true;
                }else{
                    tm.rollback();
                    return false;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
