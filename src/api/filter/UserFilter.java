package api.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class UserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String email = request.getRemoteUser();
        JSONObject jobj = new JSONObject();
        if(email==null||!request.isUserInRole("user")){
            jobj.put("error", "You are not Authorized to access this page");
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().println(jobj.toString());
            return;
        }
        chain.doFilter(request, response);
    }
    
}
