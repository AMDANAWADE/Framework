package Utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import runner.Runner;

import java.lang.reflect.Method;
import java.util.List;
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
        if (isApiTest) {
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

    public static RequestSpecification requestSpecification() {
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
        browserName = (browserName.equalsIgnoreCase("null")) ? "Chrome" : browserName;
        ExtentFactory.getInstance().getExtent().assignCategory(browserName);
        DriverFactory.setDriver(browserName);
        driver = DriverFactory.getDriver();
        driver.navigate().to(prop.getProperty("APPLICATION_URL"));
        driver.manage().window().maximize();
    }

    @DataProvider(name = "input_data", parallel = true)
    public Object[][] getInputData(ITestNGMethod testNGMethod) {
        List<Integer> row_indexes;
        Map<String, String> tc_data_from_runner;
        Integer row_index = Runner.tc_names.get(testNGMethod.getDescription());
        tc_data_from_runner = excelHandlingRunner.getData(row_index);
        ExcelHandling excelHandlingDatafile = new ExcelHandling(tc_data_from_runner.get("TC_DATAFILE"), tc_data_from_runner.get("TC_DATASHEET"));
        row_indexes = excelHandlingDatafile.get_row_indexes_having_value_in_column(excelHandlingDatafile.getHeaderRow(), 0, tc_data_from_runner.get("TC_NAME"));
        int data_size = row_indexes.size();
        Object[][] data_array = new Object[data_size][1];
        for (int i = 0; i < data_size; i++) {
            data_array[i] = new Object[]{excelHandlingDatafile.getData(row_indexes.get(i))};
        }
        return data_array;
    }

    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            DriverFactory.getDriver().quit();
            ExtentFactory.getInstance().getExtent().assignCategory("WEB UI TEST");
        } else {
            ExtentFactory.getInstance().getExtent().assignCategory("API TEST");
        }
    }


}
