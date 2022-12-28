package api.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class IsLoggedIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getRemoteUser();
        JSONObject jobj = new JSONObject();
        HttpSession session = req.getSession(false);
        if(session==null||email==null){
            jobj.put("error", "user not found");
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj.toString());
            return;
        }
        String role = (req.isUserInRole("admin"))?"admin":"user";
        jobj.put("email", email);
        jobj.put("role", role);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj.toString());
    }
}
