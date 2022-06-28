package Utilities;

import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static String editJsonValue(String jsonPath, String jsonValue, String jsonRequestBody)  {
        try {
            return JsonPath.parse(jsonRequestBody).set(jsonPath, jsonValue).jsonString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String getJsonValue(Response response,String title) {
        try {
            return response.path(title).toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
