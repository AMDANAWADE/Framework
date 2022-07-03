package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import Utilities.ExtentFactory;
import Utilities.Log;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;


public class LoginPage {

    private WebDriver driver;
    By Account = By.xpath("//a[@id='nav-link-accountList']");
    By Email_Text = By.xpath("//input[@id='ap_email']");
    By Continue = By.xpath("//input[@id='continue']");
    By Password = By.xpath("//input[@type='password']");
    By signin = By.id("signInSubmit");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login()  {
        BaseClass baseClass = new BaseClass();
        CommonWebActions webActions = new CommonWebActions(driver);
        baseClass.report_log(true, "Click Signin Button");
        WebElement Account_click;
        Account_click = webActions.getWebElement(Account);
        baseClass.report_log(true, "Clicked on Sign in");
        webActions.clickButton(Account_click);
        Log.info("Account button is clicked");
        baseClass.report_log(true, "Clicked Siginin Button");
    }

    public void enter_email(String email) throws IOException {
        BaseClass baseClass = new BaseClass();
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement Email = webActions.getWebElement(Email_Text);
        Log.info("Getting email input element");
        Log.info("Entering email");
        webActions.sendKeysOnWebElement(Email, email);
        baseClass.report_log(true, "Email entered");
        WebElement Continue_click = webActions.getWebElement(Continue);
        Log.info("Clicking on continue");
        webActions.clickButton(Continue_click);
        Log.info("Clicked on continue");
    }

    public void enter_password(String password) throws IOException {
        BaseClass baseClass = new BaseClass();
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement enter_password = webActions.getWebElement(Password);
        Log.info("Getting email input element");
        Log.info("Entering password");
        webActions.sendKeysOnWebElement(enter_password, password);
        baseClass.report_log(true, "Password Entered");
        WebElement click_signin = webActions.getWebElement(signin);
        Log.info("Clicking Sign in button");
        webActions.clickButton(click_signin);
        Log.info("Sign in Button is clicked");
    }

    public void verify_homepage(){
        BaseClass baseClass = new BaseClass();
        String ActualTitle = driver.getTitle();
        baseClass.report_log(true, "Getting page title");
        String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
        baseClass.report_log(true, "Verified page title");
    }
}
