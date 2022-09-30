package service.auth;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.controller.TwoFAAuth;
import com.dao.AdminDao;
import com.dao.AdminSecretDao;
import com.model.Admin;
import com.model.AdminSecret;

public class AdminSignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        JSONObject jObject = new JSONObject(sb.toString());
        String name = jObject.getString("name");
        String phone = jObject.getString("phone");
        String email = jObject.getString("email");
        String password = jObject.getString("password");
        Admin a = new Admin(name, phone, email, password);
        AdminDao adao = Singleton.getAdminDao();
        String secret = TwoFAAuth.generateSecretKey();
        AdminSecret as = new AdminSecret(a.getEmpid(), secret);
        AdminSecretDao asdao = Singleton.getAdminSecretDao();
        boolean s1 = adao.saveAdmin(a);
        boolean s2 = asdao.saveSecret(as);
        if (s1 && s2) {
            JSONObject jobj = new JSONObject();
            jobj.put("status", "success");
            jobj.put("secret", secret);
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj.toString());
            return;
        }
        resp.setStatus(500);
        resp.setContentType("text/html");
        resp.getWriter().println("Something went wrong...");
        return;
    }

}
