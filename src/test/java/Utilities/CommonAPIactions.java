package Utilities;

import io.restassured.response.Response;
import api_tests.BaseClass_Api;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class CommonAPIactions extends BaseClass_Api {
    private static BufferedReader read_product=null;
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static final String SINGLE_USER_API = "/api/users/4";
    public static final String CREATE_USER_API = "/api/users";
    public static final String UPDATE_USER_API = "/api/users/2";
    public static final String DELETE_USER_API = "/api/users/3";

    public static void validateStatusCode(Response response,int expected) {
        response.then().assertThat().statusCode(expected);
  }

    public static Response postCall(String endPoint, String jsonObject,String headerKey,String headerValue) throws IOException {
        return requestSpecification().body(jsonObject).when().header(headerKey,headerValue).post(endPoint);
    }
    public static Response putCall(String endPoint,String jsonObject,String headerKey,String headerValue) throws IOException {
        return requestSpecification().body(jsonObject).when().header(headerKey,headerValue).put(endPoint);
    }
    public static Response getCall(String endPoint,String headerKey,String headerValue) throws IOException {
        return requestSpecification().when().header(headerKey,headerValue).get(endPoint);
    }
    public static Response deleteCall(String endPoint,String headerKey,String headerValue) throws IOException {
        return requestSpecification().when().header(headerKey,headerValue).delete(endPoint);
    }
}
