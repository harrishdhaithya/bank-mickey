package service.auth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.controller.TwoFAAuth;

public class EvalAuth extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String code = req.getParameter("code");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);
        if(session==null){
            resp.setStatus(400);
            System.out.println("No Session");
            out.println("Something went wrong...");
            return;
        }
        String email = (String)session.getAttribute("email");
        String password = (String)session.getAttribute("password");
        String secret = (String)session.getAttribute("secret");
        String acode = TwoFAAuth.getTOTPCode(secret);
        if(code.equals(acode)){
            session.removeAttribute("email");
            session.removeAttribute("password");
            session.removeAttribute("secret");
            req.login(email, password);
            if(req.isUserInRole("admin")){
                resp.sendRedirect("/bank1/menu/adminmenu.jsp");
            }else if(req.isUserInRole("user")){
                resp.sendRedirect("/bank1/menu/usermenu.jsp");
            }
            return;
        }
        resp.setStatus(400);
        out.println("Incorrect code...");
        return;
    }
}
