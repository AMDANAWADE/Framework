package tests.pages;

import Utilities.JsonHandler;

public class PutPage {
    public static String jsonRequestBody;

    public static String putUpdateUserPayload(String jsonDataFilePath) throws Exception {
        jsonRequestBody= JsonHandler.readFileAsString(jsonDataFilePath);
        jsonRequestBody=JsonHandler.editJsonValue("$.name","gokul",jsonRequestBody);
        jsonRequestBody=JsonHandler.editJsonValue("$.job","dev",jsonRequestBody);
        return jsonRequestBody;
    }
}
