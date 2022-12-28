package com.auth;


import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import com.Singleton.Singleton;
import com.dao.LoginDao;
import com.model.Login;

public class JaasLoginModule implements LoginModule {
    private CallbackHandler callbackHandler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    String role;
    

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {
        this.subject=subject;
        this.callbackHandler=callbackHandler;
        
    }

    @Override
    public boolean login() throws LoginException {
        System.out.println("Reached login Module...");
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
        try {
            callbackHandler.handle(callbacks);
            String email = ((NameCallback)callbacks[0]).getName();
            String password = new String(((PasswordCallback)callbacks[1]).getPassword());
            LoginDao ldao = Singleton.getLoginDao();
            Login user = ldao.getLoginByEmail(email);
            if(user.evalPassword(password)){
                login=user.getEmail();
                role=user.getRole();
                return true;
            }else{
                throw new LoginException("Password is incorrect...");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new LoginException("Authentication Failed...");
        }
    }
    @Override
    public boolean abort() throws LoginException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        userPrincipal = new UserPrincipal(login);
        rolePrincipal = new RolePrincipal(role);
        subject.getPrincipals().add(userPrincipal);
        subject.getPrincipals().add(rolePrincipal);
        return true;
    }
    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }

    
}
