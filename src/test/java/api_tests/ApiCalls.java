package api_tests;

import Utilities.JsonHandler;
import Utilities.PropertiesFileHandler;
import Utilities.CommonAPIactions;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class ApiCalls extends BaseClass_Api {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static String jsonRequestBody;
    public static String key="id";
    public static String getPath="4";
    public static String putPath="2";
    public static String deletePath="3";
    public static String putUpdateUserPayload(String jsonDataFilePath){
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "gokul", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "dev", jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String postSingleUserPayload(String jsonDataFilePath){
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "value", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "testing", jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public static void getSingleUserCall() {
        try {
            response = CommonAPIactions.getCall(CommonAPIactions.SINGLE_USER_API,key,getPath,null,null,null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_SINGLE_USER_SCHEMA"));
            System.out.println("Get Call Executed "+ response.asString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public static void getListUsersCall() {
        try {
            HashMap<String,String> m= new HashMap<>();
            m.put("page","2");
            response = CommonAPIactions.getCall(CommonAPIactions.LIST_USER_API,null,null,null,m,null);
            CommonAPIactions.validateStatusCode(response, 200);
            System.out.println("Get Call Executed "+response.asString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public static void postCreateUserCall(){
        try {
            response = CommonAPIactions.postCall(CommonAPIactions.CREATE_USER_API, postSingleUserPayload(prop.getProperty("POSTJSONDATA")),null,null,null,null,null);
            CommonAPIactions.validateStatusCode(response, 201);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("POST_CREATE_USER_SCHEMA"));
            validatePostSingleUserResponse(response);
            System.out.println("Post Call Executed "+response.asString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public static void putUpdateUserCall() {
        try {
            response = CommonAPIactions.putCall(CommonAPIactions.UPDATE_USER_API, putUpdateUserPayload(prop.getProperty("POSTJSONDATA")),key,putPath,null,null,null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
            validatePutUpdateUserResponse(response);
            System.out.println("Put Call Executed "+response.asString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public static void deleteUserCall(){
        try {
            response = CommonAPIactions.deleteCall(CommonAPIactions.DELETE_USER_API,key,deletePath,null,null,null);
            CommonAPIactions.validateStatusCode(response, 204);
            System.out.println("Delete Call Executed "+response.asString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
