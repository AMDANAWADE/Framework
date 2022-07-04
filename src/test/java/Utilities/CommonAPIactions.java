package Utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;


public class CommonAPIactions {
    private static BufferedReader read_product = null;
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");;

    public static RequestSpecification reqSpec;

    /***
     * This method is to validate response status code
     * @param response json response
     * @param expected is expected status code
     */
    public static void validateStatusCode(Response response, int expected) {
        response.then().assertThat().statusCode(expected);
    }

    /***
     * This method is to make get call
     * @return returns response
     */
    public static Response getCall(String endPoint, String pathKey, String pathValue, HashMap<String, String> headers, HashMap<String, String> queryParams, String authToken) throws Exception {
        reqSpec = createRequest(pathKey, pathValue, headers, queryParams, authToken);
        return reqSpec.get(endPoint);
    }

    /***
     * This method is to make post call
     * @return returns response
     */
    public static Response postCall(String endPoint, String jsonObject, String pathKey, String pathValue, HashMap<String, String> headers, HashMap<String, String> queryParams, String authToken) throws Exception {
        reqSpec = createRequest(pathKey, pathValue, headers, queryParams, authToken);
        return reqSpec.body(jsonObject).post(endPoint);
    }

    /***
     * This method is to make put call
     * @return returns response
     */
    public static Response putCall(String endPoint, String jsonObject, String pathKey, String pathValue, HashMap<String, String> headers, HashMap<String, String> queryParams, String authToken) throws Exception {
        reqSpec = createRequest(pathKey, pathValue, headers, queryParams, authToken);
        return reqSpec.body(jsonObject).put(endPoint);
    }

    /***
     * This method is to make delete call
     * @return returns response
     */
    public static Response deleteCall(String endPoint, String pathKey, String pathValue, HashMap<String, String> headers, HashMap<String, String> queryParams, String authToken) throws Exception {
        reqSpec = createRequest(pathKey, pathValue, headers, queryParams, authToken);
        return reqSpec.delete(endPoint);
    }

    /***
     * This method is to create request specification based on given parameters
     * @return returns request specification
     */
    public static RequestSpecification createRequest(String pathKey, String pathValue, HashMap<String, String> headers, HashMap<String, String> queryParams, String authToken)  {
        if (authToken == null) {
            if (headers == null && queryParams == null && pathKey == null)
                return BaseClass.requestSpecification().when();
            else if (headers == null && queryParams == null)
                return BaseClass.requestSpecification().when().pathParam(pathKey, pathValue);
            else if (queryParams == null)
                return BaseClass.requestSpecification().when().headers(headers).pathParam(pathKey, pathValue);
            else if (headers == null && pathKey == null)
                return BaseClass.requestSpecification().when().queryParams(queryParams);
            else if (pathKey == null)
                return BaseClass.requestSpecification().when().queryParams(queryParams).headers(headers);
            else if (headers == null)
                return BaseClass.requestSpecification().when().queryParams(queryParams).pathParam(pathKey, pathValue);
            else
                return BaseClass.requestSpecification().when().headers(headers).queryParams(queryParams).pathParam(pathKey, pathValue);
        } else {
            if (headers == null && queryParams == null && pathKey == null)
                return BaseClass.requestSpecification().when();
            else if (headers == null && queryParams == null)
                return BaseClass.requestSpecification().when().auth().oauth2(authToken).pathParam(pathKey, pathValue);
            else if (queryParams == null)
                return BaseClass.requestSpecification().when().auth().oauth2(authToken).headers(headers).pathParam(pathKey, pathValue);
            else if (headers == null && pathKey == null)
                return BaseClass.requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams);
            else if (pathKey == null)
                return BaseClass.requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams).headers(headers);
            else if (headers == null)
                return BaseClass.requestSpecification().when().auth().oauth2(authToken).queryParams(queryParams).pathParam(pathKey, pathValue);
            else
                return BaseClass.requestSpecification().when().auth().oauth2(authToken).headers(headers).queryParams(queryParams).pathParam(pathKey, pathValue);
        }
    }

    /***
     * This method is to get authorization token by api call
     * @return returns authorization token as string
     */
    public static String getAccessTokenForOAuth2(String endpoint, String Username, String Password,String oauthUsername, String oauthPassword, String grantType, String scope, String accessToken){
        Response response;
        try {
            if(grantType.equalsIgnoreCase("password")){
                response = BaseClass.requestSpecification().auth().preemptive().basic(Username,Password)
                        .formParam("grant_type", grantType)
                        .formParam("username", oauthUsername)
                        .formParam("password", oauthPassword)
                        .when()
                        .post(endpoint);
            }else {
                response = BaseClass.requestSpecification().auth().preemptive().basic(Username, Password)
                        .formParam("grant_type", grantType)
                        .formParam("client_id", oauthUsername)
                        .formParam("client_secret", oauthPassword)
                        .formParam("scope", scope)
                        .when()
                        .post(endpoint);
            }
            System.out.println("OAuth Response - " + response.getBody().asString());
            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            return jsonObject.get(accessToken).toString();
        }catch (Exception e){
            Log.error("Failed to fetch Oauth token:" + e);
        }
        return null;
    }
}
