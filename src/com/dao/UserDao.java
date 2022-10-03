package com.dao;


import java.util.*;

import javax.transaction.TransactionManager;

import com.Singleton.Singleton;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.ds.query.UpdateQuery;
import com.adventnet.ds.query.UpdateQueryImpl;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Persistence;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.model.User;
import com.model.UserSecret;

public class UserDao {
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try{
            DataObject dObj = DataAccess.get("Users",(Criteria)null);
            Iterator<?> itr = dObj.getRows("Users");
            while(itr.hasNext()){
                Row row = (Row)itr.next();
                long accno = row.getLong("ACCNO");
                String fname = row.getString("FNAME");
                String lname = row.getString("LNAME");
                String phone = row.getString("PHONE");
                String email = row.getString("EMAIL");
                String passwordHash = row.getString("PASSWORD");
                Double balance = row.getBigDecimal("BALANCE").doubleValue();
                User user = new User(accno, fname, lname, phone, email, passwordHash, balance);
                users.add(user);
            }
            System.out.println(users.toString());
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return users;
    }
    public User getUserByAccno(long accno){
        Criteria c = new Criteria(new Column("Users", "ACCNO"), accno, QueryConstants.EQUAL);
        User u = null;
        try{
            DataObject dObj = DataAccess.get("Users",c);
            Iterator<?> it = dObj.getRows("Users");
            if(it.hasNext()){
                Row row = (Row)it.next();
                String email = row.getString("EMAIL");
                String fname = row.getString("FNAME");
                String lname = row.getString("LNAME");
                String phone = row.getString("PHONE");
                String passwordHash = row.getString("PASSWORD");
                Double balance = row.getBigDecimal("BALANCE").doubleValue();
                u = new User(accno, fname, lname, phone, email, passwordHash, balance);
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return u;
    }
    public User getUserByEmail(String email){
        Criteria c = new Criteria(new Column("Users", "EMAIL"), email, QueryConstants.EQUAL);
        User u = null;
        try{
            DataObject dObj = DataAccess.get("Users",c);
            Iterator<?> it = dObj.getRows("Users");
            if(it.hasNext()){
                Row row = (Row)it.next();
                long accno = row.getLong("ACCNO");
                String fname = row.getString("FNAME");
                String lname = row.getString("LNAME");
                String phone = row.getString("PHONE");
                String passwordHash = row.getString("PASSWORD");
                double balance = row.getBigDecimal("BALANCE").doubleValue();
                u = new User(accno, fname, lname, phone, email, passwordHash, balance);
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return u;
    }
    public boolean updateUser(User u){
        try{
            Persistence per = (Persistence)BeanUtil.lookup("Persistence");
            UpdateQuery uq = new UpdateQueryImpl("Users");
            Criteria c = new Criteria(new Column("Users", "ACCNO"), u.getAccno(), QueryConstants.EQUAL);
            uq.setCriteria(c);
            uq.setUpdateColumn("BALANCE", u.getBalance());
            per.update(uq);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    public boolean saveUser(User u){
        // long accno = u.getAccno();
        String fname = u.getFname();
        String lname = u.getLname();
        String email = u.getEmail();
        String phone = u.getPhone();
        String passwordHash = u.getPasswordHash();
        Double balance = u.getBalance();
        DataObject dObj = new WritableDataObject();
        Row row = new Row("Users");
        // row.set("ACCNO", accno);
        row.set("FNAME", fname);
        row.set("LNAME", lname);
        row.set("EMAIL", email);
        row.set("PHONE", phone);
        row.set("PASSWORD", passwordHash);
        row.set("BALANCE", balance);
        try {
            Persistence p = (Persistence)BeanUtil.lookup("Persistence");
            dObj.addRow(row);
            p.add(dObj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addUser(User user,String secret){
        TransactionManager tm = DataAccess.getTransactionManager();
        boolean success = false;
        try{
            tm.begin();
            {
                boolean s1 = this.saveUser(user);
                UserSecretDao usdao = Singleton.getUserSecretDao();
                UserDao udao = Singleton.getUserDao();
                System.out.println(udao.getUserByEmail(user.getEmail()).getAccno());
                boolean s2 = usdao.saveSecret(new UserSecret(udao.getUserByEmail(user.getEmail()).getAccno(), secret));
                success = s1&&s2;
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
