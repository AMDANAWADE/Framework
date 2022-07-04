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
        WebElement orders = webActions.getWebElement(OrdersBtn);
        webActions.clickElement(orders);
        report_log(true, "Signing in");
        WebElement Email = webActions.getWebElement(Input_email);
        webActions.sendKeysOnWebElement(Email, email);
        report_log(true, "Entered email");
        driver.findElement(Continue).click();
        WebElement Password = webActions.getWebElement(Input_password);
        report_log(true, "Entered Password");
        webActions.sendKeysOnWebElement(Password, password);
        driver.findElement(Signin).click();
        Assert.assertEquals(driver.findElement(Orders_title).getText(), "Your Orders");
        report_log(true, "Opened order page and verified page order title");
    }

    public void click_on_language_options(){
        CommonWebActions webActions = new CommonWebActions(driver);
        report_log(true, "Click on select languages");
        WebElement lang = webActions.getWebElement(ChangeLang);
        webActions.clickElement(lang);
        Assert.assertEquals(driver.findElement(LanguageSettingsTitle).getText(), "Language Settings");
        report_log(true, "Clicked on select languages and verified page title");
    }

}
