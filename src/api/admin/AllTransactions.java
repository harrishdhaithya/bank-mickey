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

public class AllTransactions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = tdao.getAllTransaction();
        JSONArray jArray = new JSONArray();
        for(Transaction trans:transactions){
            JSONObject jobj = new JSONObject();
            jobj.put("id", trans.getId());
            jobj.put("src", trans.getSrc());
            jobj.put("dest", trans.getDest());
            jobj.put("amount", trans.getAmount());
            jobj.put("date", trans.getDate());
            jobj.put("time", trans.getTime());
            jArray.put(jobj);
        }
        JSONObject resultObject = new JSONObject();
        resultObject.put("transactions",jArray);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(resultObject.toString());
        return;
    }
}
