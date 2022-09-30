package com.filegen.meta;

import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.*;
public class RecordMetaData {
    public static String getMetaData(){
        FileReader reader = null;
        JSONObject data = null;
        String jsonString = null;
        JSONParser parser = new JSONParser();
        try {
            reader = new FileReader("../webapps/bank1/records/metadata/format.json");
            data = (JSONObject)parser.parse(reader);
            jsonString = data.toString();
            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
