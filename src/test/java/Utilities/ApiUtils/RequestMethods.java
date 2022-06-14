package Utilities.ApiUtils;

import io.restassured.response.Response;

import java.io.IOException;

import static tests.BaseClass_Api.requestSpecification;

public class RequestMethods {
    public static Response postCall(String endPoint) throws IOException {
        return requestSpecification().when().post(endPoint);
    }
    public static Response putCall(String endPoint) throws IOException {
        return requestSpecification().when().put(endPoint);
    }
    public static Response getCall(String endPoint) throws IOException {
        return requestSpecification().when().get(endPoint);
    }
}
