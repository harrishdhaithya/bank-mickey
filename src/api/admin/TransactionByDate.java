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

public class TransactionByDate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        String date = req.getParameter("date");
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = tdao.getTransactionsByDate(date);
        JSONArray jArray = new JSONArray();
        for(Transaction transaction:transactions){
            JSONObject jobj = new JSONObject();
            jobj.put("id", transaction.getId());
            jobj.put("src", transaction.getSrc());
            jobj.put("dest", transaction.getDest());
            jobj.put("amount", transaction.getAmount());
            jobj.put("date", transaction.getDate());
            jobj.put("time", transaction.getTime());
            jArray.put(jobj);
        }
        JSONObject resulObject = new JSONObject();
        resulObject.put("transactions", jArray);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(resulObject.toString());
        return;
    }
}
