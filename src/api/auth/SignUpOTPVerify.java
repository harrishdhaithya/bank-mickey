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
import com.dao.UserDao;
import com.model.User;

public class SignUpOTPVerify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        String otp = (String)session.getAttribute("otp");
        String userOtp = req.getParameter("otp");
        JSONObject jobj = new JSONObject();
        String secret = TwoFAAuth.generateSecretKey();
        UserDao udao = Singleton.getUserDao();
        if(otp.equals(userOtp)&&udao.addUser(user, secret)){
            //Handle Success
            jobj.put("message","User Created Successfully");
            jobj.put("secret", secret);
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj.toString());
            return;
        }
        //Hanle Failure
        jobj.put("error", "OTP Verification Failed...");
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj.toString());
        return;
    }
}
