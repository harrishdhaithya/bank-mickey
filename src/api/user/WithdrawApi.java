package api.user;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.model.User;
import com.Singleton.Singleton;
import com.dao.UserDao;

public class WithdrawApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String s = "";
        StringBuilder sb  = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        JSONObject jObject = new JSONObject(sb.toString());
        double amount = jObject.getDouble("amount");
        UserDao udao = Singleton.getUserDao();
        User user = udao.getUserByEmail(req.getRemoteUser());
        double balance = user.getBalance();
        JSONObject jobj = new JSONObject();
        if(amount<balance){
            balance = balance-amount;
            user.setBalance(balance);
            boolean success = udao.updateUser(user);
            if(success){
                //Handle Success
                jobj.put("message", "Transaction Successfull");
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().println(jobj.toString());
                return;
            }
        }
        //Handle Faileure
        jobj.put("error", "Transaction Failed");
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj.toString());
        return;
    }
}
