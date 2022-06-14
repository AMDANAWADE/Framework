package tests.ApiTest;

import Utilities.ApiUtils.ApiEndpoints;
import Utilities.ApiUtils.RequestMethods;
import Utilities.PropertiesFileHandler;
import Utilities.Utils;
import org.testng.annotations.Test;
import tests.BaseClass_Api;

import java.io.IOException;

public class ApiCalls extends BaseClass_Api {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    @Test
    public static void getSingleUserCall() throws IOException {
        response = RequestMethods.getCall(ApiEndpoints.SINGLE_USER_API);
//        Utils.jsonSchemaValidation(response,prop.getProperty("GET_SINGLE_USER_SCHEMA"));
        Utils.validateJsonSchema(response,"GET_SINGLE_USER_SCHEMA","get");
    }
    @Test
    public static void postCreateUserCall() throws IOException {
        response = RequestMethods.postCall(ApiEndpoints.CREATE_USER_API, Utils.payLoad());
//        Utils.jsonSchemaValidation(response,prop.getProperty("POST_CREATE_USER_SCHEMA"));
        Utils.validateJsonSchema(response,"POST_CREATE_USER_SCHEMA","post");
    }
    @Test
    public static void putUpdateUserCall() throws IOException {
        response = RequestMethods.putCall(ApiEndpoints.UPDATE_USER_API,Utils.payLoad());
//        Utils.jsonSchemaValidation(response,prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
        Utils.validateJsonSchema(response,"PUT_UPDATE_USER_SCHEMA","put");
    }
    @Test
    public static void deleteUserCall() throws IOException {
        response = RequestMethods.deleteCall(ApiEndpoints.DELETE_USER_API);
        Utils.validateJsonSchema(response,null,"delete");
    }
}
