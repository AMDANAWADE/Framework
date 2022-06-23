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
//    public static File payLoad(){
//
//        int i=0;
//        try {
//            String jsonDataFilePath = prop.getProperty("POSTJSONDATA");
//            String csvPath = prop.getProperty("POST_DATA");
//            String line="";
//            String header="";
//            //input file
//            read_product=new BufferedReader(new FileReader(csvPath));
//            //json data file
//            header=read_product.readLine();
//            String[] headers=header.split(",");
//            line=read_product.readLine();
//                String[] contents=line.split(",");
//
//                JsonHandler jsonHandler= new JsonHandler();
//                jsonHandler.editJsonFile(contents,headers,jsonDataFilePath);
//            File jsonData = new File(jsonDataFilePath);
//                return jsonData;
//        }
//        catch(Exception e) {
//        }
//        finally {
//            try {
//                read_product.close();
//            }
//            catch(Exception e) {
//
//            }
//        }
//        return null;
//    }
    public static void validateStatusCode(Response response,int expected) {
        response.then().assertThat().statusCode(expected);
  }
}
