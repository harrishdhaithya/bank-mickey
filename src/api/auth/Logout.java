package api.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getRemoteUser();
        HttpSession session = req.getSession(false);
        JSONObject jobj = new JSONObject();
        if(session==null){
            jobj.put("error", "Already Logged out...");
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj.toString());
            return;
        }
        session.invalidate();
        req.logout();
        jobj.put("message", "Logged out Successfully");
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj.toString());
    }
}
