package com.dao;

import java.util.*;
import javax.transaction.TransactionManager;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
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
import com.adventnet.persistence.xml.Do2XmlConverter;
import com.model.Transaction;
import com.model.User;

public class TransactionDao {
    public List<Transaction> getAllTransaction(){
        List<Transaction> transactions = new ArrayList<>();
        try{
            DataObject dObj = DataAccess.get("Transaction",(Criteria)null);
            Iterator<?> itr = dObj.getRows("Transaction");
            while(itr.hasNext()){
                Row row = (Row)itr.next();
                int id = row.getLong("ID").intValue();
                long src = row.getLong("SRC");
                long dest = row.getLong("DEST");
                double amount = row.getBigDecimal("AMOUNT").doubleValue();
                Date date = row.getDate("DATE");
                Time time = row.getTime("TIME");
                Transaction t = new Transaction(id, src, dest, amount, date.toString(), time.toString());
                transactions.add(t);
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return transactions;           
    }
    public List<Transaction> getTransactionsByDate(String date){
        List<Transaction> transactions = new ArrayList<>();
        Criteria c = new Criteria(new Column("Transaction", "DATE"), Date.valueOf(date),QueryConstants.EQUAL);
        try{
            DataObject dobj = DataAccess.get("Transaction",c);
            Iterator<?> it = dobj.getRows("Transaction");
            while(it.hasNext()){
                Row r = (Row)it.next();
                int id = r.getLong("ID").intValue();
                long src = r.getLong("SRC");
                long dest = r.getLong("DEST");
                double amount = r.getBigDecimal("AMOUNT").doubleValue();
                Time time = r.getTime("TIME");
                Transaction t = new Transaction(id, src, dest, amount, date, time.toString());
                transactions.add(t);
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return transactions;
   }
    public List<Transaction> getTransactionBetweenDate(String from,String to){
        List<Transaction> transactions = new ArrayList<>();
        Criteria c = new Criteria(new Column("Transaction", "DATE"), Date.valueOf(from),QueryConstants.GREATER_EQUAL);
        c.and(new Column("Transaction", "DATE"), Date.valueOf(to), QueryConstants.LESS_EQUAL);
        try{
            DataObject dobj = DataAccess.get("Transaction",c);
            Iterator<?> it = dobj.getRows("Transaction");
            while(it.hasNext()){
                Row r = (Row)it.next();
                int id = r.getLong("ID").intValue();
                long src = r.getLong("SRC");
                long dest = r.getLong("DEST");
                double amount = r.getBigDecimal("AMOUNT").doubleValue();
                Date date = r.getDate("DATE");
                Time time = r.getTime("TIME");
                Transaction t = new Transaction(id, src, dest, amount, date.toString(), time.toString());
                transactions.add(t);
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return transactions;
    }
    public List<Transaction> getTransactionsByAccno(long accno){
        List<Transaction> transactions = new ArrayList<>();
        Criteria c = new Criteria(new Column("Transaction", "SRC"), accno,QueryConstants.EQUAL);
        try{
            DataObject dobj = DataAccess.get("Transaction",c);
            Iterator<?> it = dobj.getRows("Transaction");
            while(it.hasNext()){
                Row r = (Row)it.next();
                int id = r.getLong("ID").intValue();
                long src = r.getLong("SRC");
                long dest = r.getLong("DEST");
                double amount = r.getBigDecimal("AMOUNT").doubleValue();
                Date date = r.getDate("DATE");
                Time time = r.getTime("TIME");
                Transaction t = new Transaction(id, src, dest, amount, date.toString(), time.toString());
                transactions.add(t);
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return transactions;
    }
    public boolean writeTransaction(long src, long dest,double amount){
        Row row = new Row("Transaction");
        row.set("SRC", src);
        row.set("DEST", dest);
        row.set("AMOUNT",amount);
        row.set("DATE", Date.valueOf(LocalDate.now()));
        row.set("TIME",Time.valueOf(LocalTime.now()));
        DataObject dObj = new WritableDataObject();
        try{
            dObj.addRow(row);
            Persistence per = (Persistence)BeanUtil.lookup("Persistence");
            per.add(dObj);
            return true;
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return false;
    }
    public boolean performTransaction(User src,User dest,double amount) throws SQLException {
        TransactionManager trx = DataAccess.getTransactionManager();
        boolean success = false;
        try {
            trx.begin();
            {
                double srcBalance = src.getBalance();
                double destBalance = dest.getBalance();
                if(srcBalance>amount){
                    src.setBalance(srcBalance-amount);
                    dest.setBalance(destBalance+amount);
                    UserDao udao = Singleton.getUserDao();
                    boolean s1 = udao.updateUser(src);
                    boolean s2 = udao.updateUser(dest);
                    boolean s3 = this.writeTransaction(src.getAccno(), dest.getAccno(), amount);
                    success=s1&&s2&&s3;
                    if(success){
                        trx.commit();
                        return true;
                    }
                    trx.rollback();
                }else{
                    trx.rollback();
                    return false;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}