package ui_tests;

import Pages.HomePage;
import Utilities.BaseClass;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TestHomePage extends BaseClass {

    @Test(dataProvider = "input_data")
    public void test_orders(Map<String, String> input_data) throws InterruptedException, IOException {
        openBrowser(input_data.get("BROWSER"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_orders(input_data.get("EMAIL"), input_data.get("PASSWORD"));
    }

    @Test(dataProvider = "input_data")
    public void test_lang(Map<String, String> input_data) throws InterruptedException, IOException {
        openBrowser(input_data.get("BROWSER"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_lang();
    }


}
