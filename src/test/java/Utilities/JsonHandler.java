package Utilities;

import com.jayway.jsonpath.JsonPath;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {
    /***
     * This method is to read json data as string
     * @param file is file location
     * @return returns json data as string
     */
    public static String readFileAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    /***
     * This method is to edit json data
     * @param jsonPath is json file path
     * @param jsonValue new json value
     * @param jsonRequestBody is json request body
     * @return returns edited json value
     */
    public static String editJsonValue(String jsonPath, String jsonValue, String jsonRequestBody) {
        try {
            return JsonPath.parse(jsonRequestBody).set(jsonPath, jsonValue).jsonString();
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    /***
     * This method is to get json value
     * @param response is the json response from api
     * @param title is json field title
     * @return returns json value as string
     */
    public static String getJsonValue(Response response, String title) {
        try {
            return response.path(title).toString();
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    /***
     * This method is to validate response schema
     * @param response is the json response from api
     * @param jsonSchemaPath is json schema path
     */
    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }
}
