package ui_tests;

import Utilities.DriverFactory;
import Utilities.ExcelHandling;
import Utilities.PropertiesFileHandler;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import runner.Runner;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;


public class BaseClass {
    WebDriver driver = null;
    PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    String runnerFile = prop.getProperty("RUNNER_FILE_PATH");
    String dataFile = prop.getProperty("DATA_FILE_PATH");
    ExcelHandling excelHandlingRunner = new ExcelHandling(runnerFile, 0);
    ExcelHandling excelHandlingDataFile = new ExcelHandling(dataFile, 0);

    //This is before method
    @BeforeMethod
    public void setUp(Method method) {
        Map<String, String> ui_tests = Runner.ui_tests;
        Map<String, String> tc_details;
        Map<String, String> tc_data;
        //check if it api or ui test
        if (ui_tests.containsValue(method.getName())) {
            tc_details = excelHandlingRunner.get_test_details_using_classname_and_method(method.getName(), this.getClass().getSimpleName(), "TC_METHOD", "TC_CLASS");
            tc_data = excelHandlingDataFile.get_single_test_details(tc_details.get("TC_NAME"), "TC_NAME");
            openBrowser(String.valueOf(tc_data.get("BROWSER")));
        }
    }
    public void openBrowser(String browserName) {
        DriverFactory.setDriver(browserName);
        driver = DriverFactory.getDriver();
        driver.navigate().to(prop.getProperty("APPLICATION_URL"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    //This is after method
    @AfterMethod
    public void closeBrowser() {
        if (driver != null)
            DriverFactory.getDriver().quit();
    }
}
