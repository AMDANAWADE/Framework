package ui_tests;

import Pages.LoginPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestLoginPage extends BaseClass {

    @Test
    public void Amazon_Login() throws IOException {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login();
        loginPage.enter_email();
        loginPage.enter_password();
    }

    @Test
    public void Verify_Homepage_Title() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.verify_homepage();
    }

}
