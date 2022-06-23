package tests.api_tests;

import Utilities.ApiUtils.ApiEndpoints;
import Utilities.ApiUtils.RequestMethods;
import Utilities.PropertiesFileHandler;
import Utilities.Utils;
import org.testng.annotations.Test;
import tests.BaseClass_Api;
import tests.pages.PostPage;
import tests.pages.PutPage;

import java.io.IOException;

public class ApiCalls extends BaseClass_Api {
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    @Test
    public static void getSingleUserCall() throws IOException {
        response = RequestMethods.getCall(ApiEndpoints.SINGLE_USER_API);
        Utils.validateStatusCode(response,200);
        Utils.jsonSchemaValidation(response,prop.getProperty("GET_SINGLE_USER_SCHEMA"));
    }
    @Test
    public static void postCreateUserCall() throws Exception {
        response = RequestMethods.postCall(ApiEndpoints.CREATE_USER_API, PostPage.postSingleUserPayload(prop.getProperty("POSTJSONDATA")));
        Utils.validateStatusCode(response,201);
        Utils.jsonSchemaValidation(response,prop.getProperty("POST_CREATE_USER_SCHEMA"));
    }
    @Test
    public static void putUpdateUserCall() throws Exception {
        response = RequestMethods.putCall(ApiEndpoints.UPDATE_USER_API, PutPage.putUpdateUserPayload(prop.getProperty("POSTJSONDATA")));
        Utils.validateStatusCode(response,200);
        Utils.jsonSchemaValidation(response,prop.getProperty("PUT_UPDATE_USER_SCHEMA"));
    }
    @Test
    public static void deleteUserCall() throws IOException {
        response = RequestMethods.deleteCall(ApiEndpoints.DELETE_USER_API);
        Utils.validateStatusCode(response,200);
    }
}
