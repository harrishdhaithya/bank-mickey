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
import com.dao.UserDao;
import com.model.User;


public class AllUsers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        UserDao udao = Singleton.getUserDao();
        List<User> users = udao.getAllUsers();
        JSONArray jArray = new JSONArray();
        for(User user:users){
            JSONObject jobj = new JSONObject();
            jobj.put("accno", user.getAccno());
            jobj.put("fname", user.getFname());
            jobj.put("lname", user.getLname());
            jobj.put("email", user.getEmail());
            jobj.put("phone", user.getPhone());
            jobj.put("balance", user.getBalance());
            jArray.put(jobj);
        }
        JSONObject resulObject = new JSONObject();
        resulObject.put("users", jArray);
        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().println(resulObject.toString());
    }
}
