package Utilities;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

public class Utils {

    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
    }
}
