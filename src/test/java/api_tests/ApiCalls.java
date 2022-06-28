package api_tests;

import Pages.LoginPage;
import Utilities.JsonHandler;
import Utilities.PropertiesFileHandler;
import Utilities.CommonAPIactions;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    static Logger log = LogManager.getLogger(ApiCalls.class);

    public static String putUpdateUserPayload(String jsonDataFilePath){
        try {
            jsonRequestBody = JsonHandler.readFileAsString(jsonDataFilePath);
            jsonRequestBody = JsonHandler.editJsonValue("$.name", "gokul", jsonRequestBody);
            jsonRequestBody = JsonHandler.editJsonValue("$.job", "dev", jsonRequestBody);
            return jsonRequestBody;
        }
        catch (Exception e){
            log.fatal(e);
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
            log.fatal(e);
        }
        return null;
    }

    @Test
    public static void getSingleUserCall() {
        try {
            log.info("Started executing get call for single user");
            response = CommonAPIactions.getCall(CommonAPIactions.SINGLE_USER_API,key,getPath,null,null,null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("GET_SINGLE_USER_SCHEMA"));
            log.info("Get Call Executed "+ response.asString());
        }
        catch (Exception e){
            log.fatal(e);
        }
    }

    @Test
    public static void getListUsersCall() {
        try {
            log.info("Started executing get call for list of users");
            HashMap<String,String> m= new HashMap<>();
            m.put("page","2");
            response = CommonAPIactions.getCall(CommonAPIactions.LIST_USER_API,null,null,null,m,null);
            CommonAPIactions.validateStatusCode(response, 200);
            log.info("Get Call Executed "+response.asString());
        }
        catch (Exception e){
            log.fatal(e);
        }
    }

    @Test
    public static void postCreateUserCall(){
        try {
            log.info("Started executing post call for creating a user");
            response = CommonAPIactions.postCall(CommonAPIactions.CREATE_USER_API, postSingleUserPayload(prop.getProperty("POSTJSONDATA")),null,null,null,null,null);
            CommonAPIactions.validateStatusCode(response, 201);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("POST_CREATE_USER_SCHEMA"));
            validatePostSingleUserResponse(response);
            log.info("Post Call Executed "+response.asString());
        }
        catch (Exception e){
            log.fatal(e);
        }
    }
    @Test
    public static void putUpdateUserCall() {
        try {
            log.info("Started executing put call for updating a user");
            response = CommonAPIactions.putCall(CommonAPIactions.UPDATE_USER_API, putUpdateUserPayload(prop.getProperty("POSTJSONDATA")),key,putPath,null,null,null);
            CommonAPIactions.validateStatusCode(response, 200);
            JsonHandler.jsonSchemaValidation(response, prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
            validatePutUpdateUserResponse(response);
            log.info("Put Call Executed "+response.asString());
        }
        catch (Exception e){
            log.fatal(e);
        }
    }
    @Test
    public static void deleteUserCall(){
        try {
            log.info("Started executing delete call for deleting a user");
            response = CommonAPIactions.deleteCall(CommonAPIactions.DELETE_USER_API,key,deletePath,null,null,null);
            CommonAPIactions.validateStatusCode(response, 204);
            log.info("Delete Call Executed ");
        }
        catch (Exception e)
        {
            log.fatal(e);
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
