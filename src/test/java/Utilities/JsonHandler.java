package Utilities;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {
    FileWriter jsonWrite;
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public void editJsonFile(String[] contents, String[] headers,String jsonPath) throws IOException {
        try {
            String jsonString = readFileAsString(jsonPath);
            for(int i=0;i<headers.length;i++) {
                jsonString= JsonPath.parse(jsonString).set("$."+headers[i], contents[i]).jsonString();
            }
            jsonWrite = new FileWriter(jsonPath);
            jsonWrite.write(jsonString);
        }
        catch(Exception e) {
// log the exception

        }
        finally {
            jsonWrite.close();
        }
    }
}
