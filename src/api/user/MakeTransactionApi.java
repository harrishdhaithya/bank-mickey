package api.user;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.controller.Bank;
import com.dao.UserDao;

public class MakeTransactionApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = req.getReader();
        String s = "";
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        JSONObject jobj = new JSONObject(sb.toString());
        UserDao udao = Singleton.getUserDao();
        long src = udao.getUserByEmail(req.getRemoteUser()).getAccno();
        String dest = jobj.getString("accno");
        System.out.println(src);
        System.out.println(dest);
        double amount = jobj.getDouble("amount");
        JSONObject jobj1 = new JSONObject();
        boolean success = Bank.makeTransaction(src,Long.parseLong(dest), amount);
        if(success){
            jobj1.put("message", "Transaction Successfull");
            resp.setStatus(200);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj1.toString());
            return;
        }
        jobj1.put("error", "Transaction Failed");
        resp.setStatus(400);
        resp.setContentType("application/json");
        resp.getWriter().println(jobj1.toString());
        return;
    }
}
