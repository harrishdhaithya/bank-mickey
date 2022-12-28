package api.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.dao.UserDao;
import com.model.User;

public class GetUserBalance extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getRemoteUser();
        JSONObject jObject = new JSONObject();
        if(email==null){
            resp.setStatus(403);
            resp.setContentType("application/json");
            jObject.put("error", "User is not authenticated...");
            resp.getWriter().println(jObject.toString());
            return;
        }
        UserDao udao = Singleton.getUserDao();
        User u = udao.getUserByEmail(email);
        long accno = u.getAccno();
        String phone = u.getPhone();
        String fname = u.getFname();
        String lname = u.getLname();
        double balance = u.getBalance();
        jObject.put("accno", accno);
        jObject.put("email", email);
        jObject.put("fname", fname);
        jObject.put("lname", lname);
        jObject.put("phone", phone);
        jObject.put("balance", balance);
        resp.setContentType("application/json");
        resp.setStatus(200);
        resp.getWriter().println(jObject.toString());
    }
    
}