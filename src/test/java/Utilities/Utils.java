package Utilities;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import tests.BaseClass_Api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Utils extends BaseClass_Api {
    private static BufferedReader read_product=null;
    private static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static void jsonSchemaValidation(Response response, String jsonSchemaPath) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(jsonSchemaPath)));
    }
    public static JSONObject payLoad(){

        int i=0;
        try {
            String csvPath = prop.getProperty("POST_DATA");
            String line="";
            String header="";
            //input file
            read_product=new BufferedReader(new FileReader(csvPath));
            //json data file
            header=read_product.readLine();
            String[] headers=header.split(",");
            line=read_product.readLine();
                String[] contents=line.split(",");
                JSONObject jsonData = new JSONObject();
                JsonHandler jsonHandler= new JsonHandler();
                jsonData = jsonHandler.validate_json_schema_and_response(contents,headers);
                return jsonData;
        }
        catch(Exception e) {
        }
        finally {
            try {
                read_product.close();
            }
            catch(Exception e) {

            }
        }
        return null;
    }
    public static void validateJsonSchema(Response response,String path,String request) {
//        if (response.getStatusCode() == 200) {
//            Utils.jsonSchemaValidation(response, prop.getProperty(path));
//        } else if (response.getStatusCode() == 401) {
//            Utils.jsonSchemaValidation(response, Utils.getGlobalValue("invalidLoginJsonSchema"));
//        } else if (response.getStatusCode() == 400) {
//            Utils.jsonSchemaValidation(response, Utils.getGlobalValue("badInputLoginJsonSchema"));

//        }

        switch (request)
        {
            case "get":{
                        if (response.getStatusCode() == 200) {
            jsonSchemaValidation(response, prop.getProperty(path));
        }
                        break;
            }
            case "post":{
                if (response.getStatusCode() == 201) {
                    jsonSchemaValidation(response, prop.getProperty(path));
                }
                break;
            }
            case "put":{
                if (response.getStatusCode() == 200) {
                    jsonSchemaValidation(response, prop.getProperty(path));
                }
                break;
            }
            case "delete":{
                assertThat(response.getStatusCode(),is(equalTo(200)));
                break;
            }
            default:{

            }
        }
    }
//    public static void jsonConditiom(Response response,int statusCode,String path){
//                if (response.getStatusCode() == statusCode) {
//            Utils.jsonSchemaValidation(response, prop.getProperty(path));
//        } else if (response.getStatusCode() == statusCode) {
////            Utils.jsonSchemaValidation(response, Utils.getGlobalValue("invalidLoginJsonSchema"));
//        } else if (response.getStatusCode() == statusCode) {
////            Utils.jsonSchemaValidation(response, Utils.getGlobalValue("badInputLoginJsonSchema"));
//
//        }
//    }
}
