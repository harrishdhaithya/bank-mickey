package com.dao;

import java.util.Iterator;
import com.adventnet.ds.query.Column;
import com.adventnet.ds.query.Criteria;
import com.adventnet.ds.query.QueryConstants;
import com.adventnet.mfw.bean.BeanUtil;
import com.adventnet.persistence.DataAccess;
import com.adventnet.persistence.DataAccessException;
import com.adventnet.persistence.DataObject;
import com.adventnet.persistence.Persistence;
import com.adventnet.persistence.Row;
import com.adventnet.persistence.WritableDataObject;
import com.model.UserSecret;

public class UserSecretDao {
    public boolean saveSecret(UserSecret us) {
        String accno = us.getAccno();
        String secret = us.getSecret();
        Row r = new Row("UserSecret");
        r.set("ACCNO", accno);
        r.set("SECRET", secret); 
        DataObject dobj = new WritableDataObject();
        try {
            Persistence per = (Persistence)BeanUtil.lookup("Persistence");
            dobj.addRow(r);
            per.add(dobj);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getSecret(String accno){
        try{
            Criteria c = new Criteria(new Column("UserSecret","ACCNO"),accno , QueryConstants.EQUAL);
            DataObject dobj = DataAccess.get("UserSecret", c);
            Iterator<?> it = dobj.getRows("UserSecret");
            if(it.hasNext()){
                Row r = (Row)it.next();
                String secret = r.getString("SECRET");
                return secret;
            }
        }catch(DataAccessException ex){
            ex.printStackTrace();
        }
        return null;
    }
}

