package Utilities;

import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.File;
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

    public static String getJsonValue(Response response,String title) {
        String jsonString= response.path(title).toString();
        return jsonString;
    }

    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
    }
}