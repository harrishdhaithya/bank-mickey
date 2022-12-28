package api.auth;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.dao.LoginDao;
import com.model.Login;

public class LoginApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String s = "";
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        System.out.println(sb.toString());
        JSONObject jobj = new JSONObject(sb.toString());
        String email = jobj.getString("email");
        String password = jobj.getString("password");
        LoginDao ldao = Singleton.getLoginDao();
        Login login = ldao.getLoginByEmail(email);
        JSONObject jobj1 = new JSONObject();
        if(login.evalPassword(password)){
            HttpSession session = req.getSession(true);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            resp.setStatus(200);
            resp.setContentType("application/json");
            jobj1.put("message", "password validated go ahed with MFA...");
            resp.getWriter().println(jobj1.toString());
            return;
        }
        jobj1.put("error", "Email or password is incorrect...");
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj1.toString());
        return;
    }
}
