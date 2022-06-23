package tests.pages;

import Utilities.JsonHandler;

public class PostPage {
    public static String jsonRequestBody;

    public static String postSingleUserPayload(String jsonDataFilePath) throws Exception {
        jsonRequestBody= JsonHandler.readFileAsString(jsonDataFilePath);
        jsonRequestBody=JsonHandler.editJsonValue("$.name","value",jsonRequestBody);
        jsonRequestBody=JsonHandler.editJsonValue("$.job","testing",jsonRequestBody);
        return jsonRequestBody;
    }
}
