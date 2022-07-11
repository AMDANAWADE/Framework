package ui_tests;

import Pages.LoginPage;
import Utilities.BaseClass;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.util.Map;


public class TestLoginPage extends BaseClass {


    @Test(dataProvider = "input_data")
    public void amazon_login(Map<String, String> input_data) {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login();
        loginPage.enter_email(input_data.get("EMAIL"));
        loginPage.enter_password(input_data.get("PASSWORD"));
    }

    @Test(dataProvider = "input_data")
    public void verify_homepage_title(Map<String, String> input_data) {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.verify_homepage();
    }

}
