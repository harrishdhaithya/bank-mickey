package service.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.Singleton.Singleton;
import com.dao.AdminDao;
import com.dao.AdminSecretDao;
import com.dao.LoginDao;
import com.dao.UserDao;
import com.dao.UserSecretDao;
import com.model.Admin;
import com.model.Login;
import com.model.User;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s=br.readLine())!=null) {
            sb.append(s);
        }
        br.close();
        PrintWriter out = resp.getWriter();
        try{
            JSONObject obj = new JSONObject(sb.toString());
            String email = obj.getString("email");
            String password = obj.getString("password");
            if(
                email==null ||
                password==null
            ){
                resp.setStatus(400);
                resp.setContentType("text/html");
                out.println("All the fields are required...");
            }
            LoginDao ldao = Singleton.getLoginDao();
            Login login = ldao.getLoginByEmail(email);
            if(login==null){
                resp.setStatus(400);
                resp.setContentType("text/html");
                out.println("Incorrect Email");
                return;
            }
            if(login.evalPassword(password)){
                HttpSession session = req.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("password", password);
                session.setAttribute("secret", getSecret(email, login.getRole()));
                resp.setStatus(200);
                return;
            }else{
                resp.setStatus(400);
                resp.setContentType("text/html");
                out.println("Incorrect Password...");
                return;
            }
        }catch(JSONException ex){
            resp.setStatus(400);
            out.println(ex.toString());
        }

    }
}
