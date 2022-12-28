package com.Singleton;

import com.dao.AdminDao;
import com.dao.AdminSecretDao;
import com.dao.LoginDao;
import com.dao.TransactionDao;
import com.dao.UserDao;
import com.dao.UserSecretDao;

public class Singleton {
    private static final AdminDao adao=new AdminDao();
    private static final AdminSecretDao asec = new AdminSecretDao();
    private static final UserDao udao = new UserDao();
    private static final UserSecretDao usec = new UserSecretDao();
    private static final TransactionDao tdao = new TransactionDao();
    private static final LoginDao ldao = new LoginDao();
    public static AdminDao getAdminDao(){
        return adao;
    }
    public static AdminSecretDao getAdminSecretDao(){
        return asec;
    }
    public static UserDao getUserDao(){
        return udao;
    }
    public static UserSecretDao getUserSecretDao(){
        return usec;
    }
    public static TransactionDao getTransactionDao(){
        return tdao;
    }
    public static LoginDao getLoginDao(){
        return ldao;
    }
}
