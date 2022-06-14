package tests;

import Utilities.PropertiesFileHandler;
import Utilities.Utils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import java.io.IOException;

//This is base class
public class BaseClass_Api {
    public static RequestSpecification requestSpecification;

    public static Response response = null;

    @BeforeClass
    public static RequestSpecification requestSpecification() throws IOException {
        PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
        String BaseUrl = prop.getProperty("BaseUrl");
        if (requestSpecification == null) {
            requestSpecification = new RequestSpecBuilder().setBaseUri(BaseUrl)
                    .setContentType(ContentType.JSON)
                    .build();
            return RestAssured.given().spec(requestSpecification);
        }
        return RestAssured.given().spec(requestSpecification);
    }
}
