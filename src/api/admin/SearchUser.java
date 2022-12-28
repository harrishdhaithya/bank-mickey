package api.admin;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.dao.UserDao;
import com.model.User;

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        long accno = Long.parseLong(req.getParameter("accno"));
        UserDao udao = Singleton.getUserDao();
        User user = udao.getUserByAccno(accno);
        String fname = user.getFname();
        String lname = user.getLname();
        String phone = user.getPhone();
        String email = user.getEmail();
        double balance = user.getBalance();
        JSONObject jobj1 = new JSONObject();
        jobj1.put("fname", fname);
        jobj1.put("lname", lname);
        jobj1.put("accno", accno);
        jobj1.put("phone", phone);
        jobj1.put("email", email);
        jobj1.put("balance", balance);
        JSONObject resulObject = new JSONObject();
        resulObject.put("user", jobj1);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(resulObject.toString());
        return;
    }
}
