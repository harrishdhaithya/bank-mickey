package com.model;

public class Transaction {
    private int id;
    private long src;
    private long dest;
    private double amount;
    private String date;
    private String time;
    public Transaction(long src,long dest,double amount){
        this.src=src;
        this.dest=dest;
        this.amount=amount;
    }
    public Transaction(int id,long src,long dest,double amount,String date,String time){
        this.id=id;
        this.src=src;
        this.dest=dest;
        this.amount=amount;
        this.date=date;
        this.time=time;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setSrc(long u){
        this.src=u;
    }
    public void setDest(long u){
        this.src=u;
    }
    public void setAmount(double amount){
        this.amount=amount;
    }
    public void setDate(String date){
        this.date=date;
    }
    public void setTime(String time){
        this.time=time;
    }
    public int getId(){
        return id;
    }
    public long getSrc(){
        return src;
    }
    public long getDest(){
        return dest;
    }
    public double getAmount(){
        return amount;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
    @Override
    public String toString(){
        return "Source Account: " + src+"\n"+
                "Destination Account: " + dest+"\n"+
                "Transaction Amount: "+ amount +"\n"+
                "Date: "+date+"\n"+
                "Time: "+time;
    }
}
