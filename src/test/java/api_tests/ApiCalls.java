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

    @Test
    public static String putUpdateUserPayload(String jsonDataFilePath, String name, String job) {
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", name, jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", job, jsonRequestBody);
            return jsonRequestBody;
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    @Test
    public static String postSingleUserPayload(String jsonDataFilePath, String name, String job) {
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", name, jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", job, jsonRequestBody);
            return jsonRequestBody;
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
        return null;
    }

    @Test(dataProvider = "input_data")
    public void getSingleUserCall(Map<String, String> input_data) {
        // String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
        try {
            report_log(true,"Started executing get call for single user");
            Response response = CommonAPIactions.getCall(
                    input_data.get("ENDPOINT"), input_data.get("PATH_KEY"), input_data.get("PATH_VALUE"), null, null, null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_SINGLE_USER_SCHEMA"));
            report_log(true,"Get Single User Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test(dataProvider = "input_data")
    public void getListUsersCall(Map<String, String> input_data) {
        //  String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
        try {
            report_log(true,"Started executing get call for list of users");
            HashMap<String, String> m = new HashMap<>();
            m.put(input_data.get("QUERY_KEY"), input_data.get("QUERY_VALUE"));
            Response response = CommonAPIactions.getCall(input_data.get("ENDPOINT"), null, null, null, m, null);
            CommonAPIactions.validateStatusCode(response, 200);
            report_log(true,"Get List of Users Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test(dataProvider = "input_data")
    public void postCreateUserCall(Map<String, String> input_data) {
        // String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
        try {
            report_log(true,"Started executing post call for creating a user");
            Response response = CommonAPIactions.postCall(input_data.get("ENDPOINT"), postSingleUserPayload(prop.getProperty("POSTJSONDATA"), input_data.get("NAME"), input_data.get("JOB")), null, null, null, null, null);
            CommonAPIactions.validateStatusCode(response, 201);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("POST_CREATE_USER_SCHEMA"));
            validatePostSingleUserResponse(response, input_data.get("NAME"), input_data.get("JOB"));
            report_log(true,"Post Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test(dataProvider = "input_data")
    public void putUpdateUserCall(Map<String, String> input_data) {
        // String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
        try {
           report_log(true,"Started executing put call for updating a user");
            Response response = CommonAPIactions.putCall(input_data.get("ENDPOINT"), putUpdateUserPayload(prop.getProperty("POSTJSONDATA"), input_data.get("NAME"), input_data.get("JOB")), input_data.get("PATH_KEY"), input_data.get("PATH_VALUE"), null, null, null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
            validatePutUpdateUserResponse(response, input_data.get("NAME"), input_data.get("JOB"));
            report_log(true,"Put Call Executed " + response.asString());
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    @Test(dataProvider = "input_data")
    public void deleteUserCall(Map<String, String> input_data) {
        //String authToken = getAccessTokenForOAuth2(endpoint, username, password, oauthUsername, oauthPassword, grantType, scope, expectedTokenName);
        try {
            report_log(true,"Started executing delete call for deleting a user");
            Response response = CommonAPIactions.deleteCall(input_data.get("ENDPOINT"), input_data.get("PATH_KEY"), input_data.get("PATH_VALUE"), null, null, null);
            CommonAPIactions.validateStatusCode(response, 204);
            report_log(true, "Delete Call executed");
        } catch (Exception e) {
            Log.fatal(e.getMessage());
        }
    }

    public static void validatePostSingleUserResponse(Response res, String name, String job) {
        Assert.assertEquals(JsonHandler.getJsonValue(res, "name"), name);
        Assert.assertEquals(JsonHandler.getJsonValue(res, "job"), job);
    }

    public static void validatePutUpdateUserResponse(Response res, String name, String job) {
        Assert.assertEquals(JsonHandler.getJsonValue(res, "name"), name);
        Assert.assertEquals(JsonHandler.getJsonValue(res, "job"), job);
    }
}
