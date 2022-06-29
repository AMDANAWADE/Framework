package Utilities;

import io.restassured.response.Response;
import api_tests.BaseClass_Api;
import io.restassured.specification.RequestSpecification;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class CommonAPIactions extends BaseClass_Api {
    private static BufferedReader read_product=null;
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static final String SINGLE_USER_API = "/api/users/{id}";
    public static final String LIST_USER_API = "/api/users/";
    public static final String CREATE_USER_API = "/api/users";
    public static final String UPDATE_USER_API = "/api/users/{id}";
    public static final String DELETE_USER_API = "/api/users/{id}";
    public static RequestSpecification reqSpec;

    public static void validateStatusCode(Response response,int expected) {
        response.then().assertThat().statusCode(expected);
  }

    public static Response getCall(String endPoint,String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.get(endPoint);
    }

    public static Response postCall(String endPoint, String jsonObject,String pathKey,String pathValue, HashMap<String,String> headers, HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.body(jsonObject).post(endPoint);
    }

    public static Response putCall(String endPoint,String jsonObject,String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.body(jsonObject).put(endPoint);
    }

    public static Response deleteCall(String endPoint,String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws Exception {
        reqSpec=createRequest(pathKey,pathValue,headers, queryParams,authToken);
        return reqSpec.delete(endPoint);
    }

    public static RequestSpecification createRequest(String pathKey,String pathValue,HashMap<String,String> headers,HashMap<String,String> queryParams,String authToken) throws IOException {
        if (authToken == null) {
            if(headers == null && queryParams == null && pathKey==null)
                return requestSpecification().when();
            else if (headers == null && queryParams == null)
                return requestSpecification().when().pathParam(pathKey, pathValue);
            else if (queryParams == null)
                return requestSpecification().when().headers(headers).pathParam(pathKey, pathValue);
            else if (headers == null && pathKey == null)
                return requestSpecification().when().queryParams(queryParams);
            else if (pathKey == null)
                return requestSpecification().when().queryParams(queryParams).headers(headers);
            else if (headers == null)
                return requestSpecification().when().queryParams(queryParams).pathParam(pathKey, pathValue);
            else
                return requestSpecification().when().headers(headers).queryParams(queryParams).pathParam(pathKey, pathValue);
        }
        else {
            if(headers == null && queryParams == null && pathKey==null)
                return requestSpecification().when();
            else if (headers == null && queryParams == null)
                return requestSpecification().when().auth().oauth2(authToken).pathParam(pathKey, pathValue);
            else if (queryParams == null)
                return requestSpecification().when().auth().oauth2(authToken).headers(headers).pathParam(pathKey, pathValue);
            else if (headers == null && pathKey == null)
                return requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams);
            else if (pathKey == null)
                return requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams).headers(headers);
            else if (headers == null)
                return requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams).pathParam(pathKey, pathValue);
            else
                return requestSpecification().when().auth().oauth2(authToken).headers(headers).queryParams(queryParams).pathParam(pathKey, pathValue);
        }
    }
}
