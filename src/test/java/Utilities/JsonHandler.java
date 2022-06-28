package Utilities;

import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    public static String readFileAsString(String file) throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public static String editJsonValue(String jsonPath, String jsonValue, String jsonRequestBody) {
        String jsonString = JsonPath.parse(jsonRequestBody).set(jsonPath, jsonValue).jsonString();
        return jsonString;
    }

    public static String getJsonValue(Response response, String title) {
        String jsonString = response.path(title).toString();
        return jsonString;
    }

    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
    }
}
