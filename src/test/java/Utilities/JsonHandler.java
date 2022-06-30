package Utilities;

import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    public static String readFileAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    public static String editJsonValue(String jsonPath, String jsonValue, String jsonRequestBody) {
        try {
            return JsonPath.parse(jsonRequestBody).set(jsonPath, jsonValue).jsonString();
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    public static String getJsonValue(Response response, String title) {
        try {
            return response.path(title).toString();
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }
}
