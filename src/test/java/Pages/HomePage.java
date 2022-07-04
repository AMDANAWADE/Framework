package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class HomePage extends BaseClass {

    public WebDriver driver;

    By Input_email = By.xpath("//input[@id='ap_email']");
    By Continue = By.xpath("//input[@id='continue']");
    By Input_password = By.xpath("//input[@type='password']");
    By Orders_title = By.xpath("//h1[text()='Your Orders']");
    By Signin = By.id("signInSubmit");
    By OrdersBtn = By.xpath("(//span[@class='nav-line-2'])[2]");
    By ChangeLang = By.xpath("//span[@class='icp-nav-flag icp-nav-flag-in']");
    By LanguageSettingsTitle = By.xpath("//h3[contains(text(),\"Language Settings\")]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click_orders(String email, String password) throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        report_log(true, "Click on orders");
        Log.info("Clicking on orders");
        WebElement orders = webActions.getWebElement(OrdersBtn);
        webActions.clickElement(orders);
        WebElement Email = webActions.getWebElement(Input_email);
        Log.info("Entering email");
        webActions.sendKeysOnWebElement(Email, email);
        driver.findElement(Continue).click();
        WebElement Password = webActions.getWebElement(Input_password);
        Log.info("Entering password");
        webActions.sendKeysOnWebElement(Password, password);
        Log.info("Clicking Signin button");
        driver.findElement(Signin).click();
        Log.info("Clicked Signin button");
        Assert.assertEquals(driver.findElement(Orders_title).getText(), "Your Orders");
        Log.info("Verified Order Page Title");
        report_log(true, "Opened order page");
    }

    public void click_on_language_options() throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        report_log(true, "Click on select languages");
        Log.info("Click on select languages");
        WebElement lang = webActions.getWebElement(ChangeLang);
        webActions.clickElement(lang);
        Assert.assertEquals(driver.findElement(LanguageSettingsTitle).getText(), "Language Settings");
        Log.info("Verified Languages Page Title");
        report_log(true, "Clicked on select languages");
    }

}
