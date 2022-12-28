package api.admin;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.model.Transaction;

public class TransactionByAccno extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accno = req.getParameter("accno");
        if(accno ==null){   
            JSONObject jobj = new JSONObject();
            jobj.put("error", "Account Number should not be null");
            resp.setStatus(400);
            resp.setContentType("application/json");
            resp.getWriter().println(jobj.toString());
        }
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = tdao.getTodaysTransByAccno(Long.parseLong(accno));
        JSONArray jarr = new JSONArray();
        for(Transaction t:transactions){
            JSONObject jobj = new JSONObject();
            jobj.put("id", t.getId());
            jobj.put("src", t.getSrc());
            jobj.put("dest", t.getDest());
            jobj.put("amount", t.getAmount());
            jobj.put("date", t.getDate());
            jobj.put("time", t.getTime());
            jarr.put(jobj);
        }
        JSONObject resObj = new JSONObject();
        resObj.put("transactions", jarr);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(resObj.toString());
    }
}
