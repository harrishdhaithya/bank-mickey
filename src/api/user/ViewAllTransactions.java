package api.user;

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
import com.dao.UserDao;
import com.model.Transaction;
import com.model.User;

public class ViewAllTransactions extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException{
        String email = req.getRemoteUser();
        UserDao udao = Singleton.getUserDao();
        User user = udao.getUserByEmail(email);
        TransactionDao tdao = Singleton.getTransactionDao();
        List<Transaction> transactions = tdao.getTransactionsByAccno(user.getAccno());
        JSONArray jArray = new JSONArray();
        System.out.println(transactions.size());
        for(Transaction transaction:transactions){
            JSONObject jobj = new JSONObject();
            jobj.put("id", transaction.getId());
            jobj.put("src", transaction.getSrc());
            jobj.put("dest",transaction.getDest());
            jobj.put("amount", transaction.getAmount());
            jobj.put("date", transaction.getDate());
            jobj.put("time", transaction.getTime());
            jArray.put(jobj);
        }
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(jArray.toString());
    }
}
