package ui_tests;

import Pages.HomePage;
import Pages.LoginPage;
import Utilities.BaseClass;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TestHomePage extends BaseClass {

    @Test(dataProvider = "input_data")
    public void test_orders(Map<String, String> input_data) throws InterruptedException, IOException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login();
        loginPage.enter_email(input_data.get("EMAIL"));
        loginPage.enter_password(input_data.get("PASSWORD"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_orders();
    }

    @Test(dataProvider = "input_data")
    public void test_language(Map<String, String> input_data) {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_on_language_options();
    }

    @Test(dataProvider = "input_data")
    public void go_to_help(Map<String, String> input_data) throws InterruptedException {
        openBrowser(input_data.get("BROWSER"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_help_link();
    }

    @Test(dataProvider = "input_data")
    public void go_to_contact_us_page(Map<String, String> input_data) throws InterruptedException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login();
        loginPage.enter_email(input_data.get("EMAIL"));
        loginPage.enter_password(input_data.get("PASSWORD"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_help_link();
        homePage.verify_contact_us_page();
    }

    @Test(dataProvider = "input_data")
    public void test_change_country(Map<String, String> input_data) throws InterruptedException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.open_change_country_settings();
    }

    @Test(dataProvider = "input_data")
    public void add_new_address(Map<String, String> input_data) throws InterruptedException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        loginPage.login();
        loginPage.enter_email(input_data.get("EMAIL"));
        loginPage.enter_password(input_data.get("PASSWORD"));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.click_your_account();
        homePage.click_your_addresses();
        homePage.add_address();
    }
    @Test(dataProvider = "input_data")
    public void go_to_new_release_page(Map<String, String> input_data) throws InterruptedException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.shop_new_releases();
    }
}
