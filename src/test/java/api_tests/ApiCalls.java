package api_tests;

import Utilities.JsonHandler;
import Utilities.PropertiesFileHandler;
import Utilities.CommonAPIactions;
import Utilities.StandardHeaders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ApiCalls extends BaseClass_Api {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static String jsonRequestBody;

    public static String putUpdateUserPayload(String jsonDataFilePath) throws Exception {
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "gokul", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "dev", jsonRequestBody);
            return jsonRequestBody;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    public static String postSingleUserPayload(String jsonDataFilePath) throws Exception {
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "value", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "testing", jsonRequestBody);
            return jsonRequestBody;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public static void getSingleUserCall() throws IOException {
        response = CommonAPIactions.getCall(CommonAPIactions.SINGLE_USER_API, StandardHeaders.AUTHENTICATION,"id");
        CommonAPIactions.validateStatusCode(response,200);
        JsonHandler.jsonSchemaValidation(response,prop.getProperty("GET_SINGLE_USER_SCHEMA"));
        System.out.println("Get Call Executed");
    }
    @Test
    public static void postCreateUserCall() throws Exception {
        response = CommonAPIactions.postCall(CommonAPIactions.CREATE_USER_API, postSingleUserPayload(prop.getProperty("POSTJSONDATA")), StandardHeaders.AUTHENTICATION,"id");
        CommonAPIactions.validateStatusCode(response,201);
        JsonHandler.jsonSchemaValidation(response,prop.getProperty("POST_CREATE_USER_SCHEMA"));
        validatePostSingleUserResponse(response);
        System.out.println("Post Call Executed");
    }
    @Test
    public static void putUpdateUserCall() throws Exception {
        response = CommonAPIactions.putCall(CommonAPIactions.UPDATE_USER_API, putUpdateUserPayload(prop.getProperty("POSTJSONDATA")), StandardHeaders.AUTHENTICATION,"id");
        CommonAPIactions.validateStatusCode(response,200);
        JsonHandler.jsonSchemaValidation(response,prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
        validatePutUpdateUserResponse(response);
        System.out.println("Put Call Executed");
    }
    @Test
    public static void deleteUserCall() throws IOException {
        response = CommonAPIactions.deleteCall(CommonAPIactions.DELETE_USER_API, StandardHeaders.AUTHENTICATION,"id");
        CommonAPIactions.validateStatusCode(response,204);
        System.out.println("Delete Call Executed");
    }

    public static void validatePostSingleUserResponse(Response res){
        Assert.assertEquals(JsonHandler.getJsonValue(res,"name"),"value");
        Assert.assertEquals(JsonHandler.getJsonValue(res,"job"),"testing");
    }

    public static void validatePutUpdateUserResponse(Response res){
        Assert.assertEquals(JsonHandler.getJsonValue(res,"name"),"gokul");
        Assert.assertEquals(JsonHandler.getJsonValue(res,"job"),"dev");
    }
}
