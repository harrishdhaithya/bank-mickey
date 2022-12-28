package api.auth;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import com.controller.Otp;
import com.mailing.Mailer;
import com.model.User;

public class SignUp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String s = "";
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        JSONObject jobj = new JSONObject(sb.toString());
        String fname = jobj.getString("fname");
        String lname = jobj.getString("lname");
        String email = jobj.getString("email");
        String password = jobj.getString("password");
        String phone = jobj.getString("phone");
        double balance = jobj.getDouble("deposit");
        User user = new User(fname, lname, phone, email, password, balance);
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        String otp = Otp.generateOTP();
        session.setAttribute("otp", otp);
        boolean success = Mailer.sendMail(email, "Your One Time Password", otp);
        JSONObject jobj1 = new JSONObject();
        if(success){
            jobj1.put("Message", "Verify Otp To Signup...");
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj1.toString());
            return;
        }
        session.invalidate();
        jobj1.put("error", "something went wrong...Try again...");
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj1.toString());
        return;
    }
}
