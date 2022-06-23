package Utilities;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {
    private static FileWriter jsonWrite;

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static String editJsonFile(String jsonPath, String jsonValue,String jsonDataPath) throws IOException {
        try {
            String jsonString = readFileAsString(jsonDataPath);
                jsonString= JsonPath.parse(jsonString).set(jsonPath, jsonValue).jsonString();
            jsonWrite = new FileWriter(jsonPath);
            jsonWrite.write(jsonString);
        }
        catch(Exception e) {
// log the exception

        }
        finally {
            jsonWrite.close();

        }
        return null;
    }

    public static String editJsonValue(String jsonPath, String jsonValue, String jsonRequestBody) {
        String jsonString= JsonPath.parse(jsonRequestBody).set(jsonPath, jsonValue).jsonString();
        return jsonString;
    }
}
