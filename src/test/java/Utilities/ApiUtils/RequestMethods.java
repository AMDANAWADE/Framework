package Utilities.ApiUtils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.io.IOException;

import static tests.BaseClass_Api.requestSpecification;

public class RequestMethods {
    public static Response postCall(String endPoint, JSONObject jsonObject) throws IOException {
        return requestSpecification().body(jsonObject).when().post(endPoint);
    }
    public static Response putCall(String endPoint,JSONObject jsonObject) throws IOException {
        return requestSpecification().body(jsonObject).when().put(endPoint);
    }
    public static Response getCall(String endPoint) throws IOException {
        return requestSpecification().when().get(endPoint);
    }
    public static Response deleteCall(String endPoint) throws IOException {
        return requestSpecification().when().get(endPoint);
    }
}
