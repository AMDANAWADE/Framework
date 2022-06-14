package Utilities;

import org.json.simple.JSONObject;

import java.io.IOException;

public class JsonHandler {
    public JSONObject validate_json_schema_and_response(String[] contents, String[] headers) throws IOException {
        try {
            JSONObject jsonData=null;
            jsonData = new JSONObject();
            for(int i=0;i<headers.length;i++)
                jsonData.put(headers[i],contents[i]);
            return jsonData;
        }
        catch(Exception e) {
// log the exception

        }
        return null;
    }
}
