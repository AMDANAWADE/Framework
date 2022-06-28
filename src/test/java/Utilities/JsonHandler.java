package Utilities;

import api_tests.ApiCalls;
import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    static Logger log = LogManager.getLogger(JsonHandler.class);

    public static String readFileAsString(String file)
    {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        }
        catch (Exception e){
            log.fatal(e);
        }
        return null;
    }

    public static String editJsonValue(String jsonPath, String jsonValue, String jsonRequestBody)  {
        try {
            return JsonPath.parse(jsonRequestBody).set(jsonPath, jsonValue).jsonString();
        }
        catch (Exception e){
            log.fatal(e);
        }
        return null;
    }

    public static String getJsonValue(Response response,String title) {
        try {
            return response.path(title).toString();
        }
        catch (Exception e){
            log.fatal(e);
        }
        return null;
    }

    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
        }
        catch (Exception e){
            log.fatal(e);
        }
    }
}
