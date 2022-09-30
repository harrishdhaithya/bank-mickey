package service.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.adventnet.persistence.xml.Do2XmlConverter;

public class Test extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DataObject dobj = new WritableDataObject();
            Row row = new Row("Users");
            row.set("ACCNO", "1234567890");
            row.set("FNAME", "Harrish");
            row.set("LNAME", "Harrish");
            row.set("PHONE", "2436473526");
            row.set("EMAIL", "harrishdhaithya@gmail.com");
            row.set("BALANCE",5000);
            dobj.addRow(row);
            resp.getWriter().println(Do2XmlConverter.getXmlFromDO(dobj, "iq", true));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            resp.getWriter().print(e);
        }
        
    }
}
