package ui_tests;

import Pages.LoginPage;
import Utilities.BaseClass;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;


public class TestLoginPage extends BaseClass {

    //This method is to verify application login
    @Test(dataProvider = "input_data")
    public void amazon_login(Map<String, String> input_data) throws IOException {
        openBrowser(input_data.get("BROWSER"));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login();
        loginPage.enter_email(input_data.get("EMAIL"));
        loginPage.enter_password(input_data.get("PASSWORD"));
        loginPage.verify_username_on_page_after_sign_in(input_data.get("USERNAME"));
    }
    //This method is to verify home page title
    @Test(dataProvider = "input_data")
    public void verify_homepage_title(Map<String, String> input_data) {
        openBrowser(input_data.get("BROWSER"));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.verify_homepage();
    }

}
