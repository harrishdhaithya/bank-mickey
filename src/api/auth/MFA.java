package api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.controller.TwoFAAuth;
import com.dao.AdminDao;
import com.dao.AdminSecretDao;
import com.dao.LoginDao;
import com.dao.UserDao;
import com.dao.UserSecretDao;
import com.model.Admin;
import com.model.Login;
import com.model.User;

public class MFA extends HttpServlet {
    private static String getSecret(String email,String role){
        if(role.equals("admin")){
            AdminDao adao = Singleton.getAdminDao();
            Admin admin = adao.getAdminByEmail(email);
            AdminSecretDao asdao = Singleton.getAdminSecretDao();
            return asdao.getSecret(admin.getEmpid());
         }else{
            UserDao udao = Singleton.getUserDao();
            User u = udao.getUserByEmail(email);
            UserSecretDao usdao = Singleton.getUserSecretDao();
            return usdao.getSecret(u.getAccno());
         }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        HttpSession session = req.getSession(false);
        String email = (String)session.getAttribute("email");
        String password = (String)session.getAttribute("password");
        LoginDao ldao = Singleton.getLoginDao();
        Login user =  ldao.getLoginByEmail(email);
        JSONObject jobj = new JSONObject();
        String totp = TwoFAAuth.getTOTPCode(getSecret(email, user.getRole()));  
        if(totp.equals(code)){
            req.getSession(true);
            req.login(email, password);
            jobj.put("message", "Login Successfull");
            jobj.put("user", email);
            jobj.put("role", user.getRole());
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj.toString());
            return;
        } 
        jobj.put("error", "Incorrect OTP Code");
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj.toString());
    }
}
