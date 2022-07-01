package ui_tests;

import Pages.HomePage;
import Utilities.BaseClass;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TestHomePage extends BaseClass {

    //This method is to verify orders page
    @Test(dataProvider = "input_data")
    public void test_orders(Map<String, String> input_data) throws InterruptedException, IOException {
        openBrowser(input_data.get("BROWSER"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_orders(input_data.get("EMAIL"), input_data.get("PASSWORD"));
    }
    //This method is to verify language settings page title
    @Test(dataProvider = "input_data")
    public void test_language(Map<String, String> input_data) throws InterruptedException, IOException {
        openBrowser(input_data.get("BROWSER"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_on_language_options();
    }


}
