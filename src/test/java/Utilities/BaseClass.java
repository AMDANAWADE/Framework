package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;
import org.testng.annotations.*;
import runner.Runner;

import java.lang.reflect.Method;
import java.util.Map;


public class BaseClass {
    public WebDriver driver = null;
    PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    String runnerFile = prop.getProperty("RUNNER_FILE_PATH");
    ExcelHandling excelHandlingRunner = new ExcelHandling(runnerFile, 0);
    public static RequestSpecification requestSpecification;
    boolean isApiTest = false;
    @BeforeMethod
    public void setUp(Method method) {
        if (Runner.api_tests.containsValue(method.getName())) {
            isApiTest = true;
            requestSpecification();
        }
    }
    public void report_log(boolean status, String message) {
        Log.info(message);
        if(isApiTest) {
            if (status)
                ExtentFactory.getInstance().getExtent().pass(message);
            else
                ExtentFactory.getInstance().getExtent().fail(message);
            return;
        }
        CommonWebActions webActions = new CommonWebActions(DriverFactory.getDriver());
        String step_screenshot_flag = prop.getProperty("STEP_SCREENSHOT");
        try {
            if (status) {
                if (step_screenshot_flag.equalsIgnoreCase("yes")) {
                    ExtentFactory.getInstance().getExtent().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
                } else
                    ExtentFactory.getInstance().getExtent().pass(message);
            } else {
                if (step_screenshot_flag.equalsIgnoreCase("yes")) {
                    ExtentFactory.getInstance().getExtent().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
                } else
                    ExtentFactory.getInstance().getExtent().fail(message);
            }
        } catch (Exception e) {
            Log.info("Unable to add test step");
        }
    }
    public static RequestSpecification requestSpecification()  {
        PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
        String BaseUrl = prop.getProperty("baseUrl");
        if (requestSpecification == null) {
            requestSpecification = new RequestSpecBuilder().setBaseUri(BaseUrl)
                    .setContentType(ContentType.JSON)
                    .build();
            return RestAssured.given().spec(requestSpecification);
        }
        return RestAssured.given().spec(requestSpecification);
    }

    public void openBrowser(String browserName) {
        DriverFactory.setDriver(browserName);
        driver = DriverFactory.getDriver();
        driver.navigate().to(prop.getProperty("APPLICATION_URL"));
        driver.manage().window().maximize();
    }

    @DataProvider(name = "input_data", parallel = true)
    public Object[][] getInputData(ITestNGMethod testNGMethod) {
        Map<String, String> tc_data_from_runner;
        Map<String, String> tc_data_from_datafile;
        Integer row_index = Runner.tc_names.get(testNGMethod.getDescription());
        tc_data_from_runner = excelHandlingRunner.getData(row_index);
        ExcelHandling excelHandlingDatafile = new ExcelHandling(tc_data_from_runner.get("TC_DATAFILE"), tc_data_from_runner.get("TC_DATASHEET"));
        tc_data_from_datafile = excelHandlingDatafile.get_single_test_details(tc_data_from_runner.get("TC_NAME"), "TC_NAME");
        return new Object[][]{{tc_data_from_datafile}};
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null)
            DriverFactory.getDriver().quit();
        ThreadContext.clearMap();
    }


}
