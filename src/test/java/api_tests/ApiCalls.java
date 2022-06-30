package api_tests;

import Utilities.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiCalls extends BaseClass {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static String jsonRequestBody;
    public static String key = "id";
    public static String getPath = "4";
    public static String putPath = "2";
    public static String deletePath = "3";

    public static String putUpdateUserPayload(String jsonDataFilePath) {
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "gokul", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "dev", jsonRequestBody);
            return jsonRequestBody;
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    @Test
    public static String postSingleUserPayload(String jsonDataFilePath) {
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "value", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "testing", jsonRequestBody);
            return jsonRequestBody;
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    @Test
    public static void getSingleUserCall() {
        try {
            Log.info("Started executing get call for single user");
            response = CommonAPIactions.getCall(CommonAPIactions.SINGLE_USER_API, key, getPath, null, null, null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_SINGLE_USER_SCHEMA"));
            Log.info("Get Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test
    public static void getListUsersCall() {
        try {
            Log.info("Started executing get call for list of users");
            HashMap<String, String> m = new HashMap<>();
            m.put("page", "2");
            response = CommonAPIactions.getCall(CommonAPIactions.LIST_USER_API, null, null, null, m, null);
            CommonAPIactions.validateStatusCode(response, 200);
            Log.info("Get Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test(dataProvider = "input_data")
    public static void postCreateUserCall(Map<String, String> input_data) {
        try {
            Log.info("Started executing post call for creating a user");
            response = CommonAPIactions.postCall(CommonAPIactions.CREATE_USER_API, postSingleUserPayload(prop.getProperty("POSTJSONDATA")), null, null, null, null, null);
            CommonAPIactions.validateStatusCode(response, 201);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("POST_CREATE_USER_SCHEMA"));
            validatePostSingleUserResponse(response);
            Log.info("Post Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test
    public static void putUpdateUserCall() {
        try {
            Log.info("Started executing put call for updating a user");
            response = CommonAPIactions.putCall(CommonAPIactions.UPDATE_USER_API, putUpdateUserPayload(prop.getProperty("POSTJSONDATA")), key, putPath, null, null, null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
            validatePutUpdateUserResponse(response);
            Log.info("Put Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test
    public static void deleteUserCall() {
        try {
            Log.info("Started executing delete call for deleting a user");
            response = CommonAPIactions.deleteCall(CommonAPIactions.DELETE_USER_API, key, deletePath, null, null, null);
            CommonAPIactions.validateStatusCode(response, 204);
            Log.info("Delete Call Executed ");
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    public static void validatePostSingleUserResponse(Response res) {
        Assert.assertEquals(JsonHandler.getJsonValue(res, "name"), "value");
        Assert.assertEquals(JsonHandler.getJsonValue(res, "job"), "testing");
    }

    public static void validatePutUpdateUserResponse(Response res) {
        Assert.assertEquals(JsonHandler.getJsonValue(res, "name"), "gokul");
        Assert.assertEquals(JsonHandler.getJsonValue(res, "job"), "dev");
    }
}
