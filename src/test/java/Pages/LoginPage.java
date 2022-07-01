package Pages;

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
    By title_after_signin = By.xpath("contains(@id,\"nav-link-accountList-nav-line-1\")]");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Click Signin Button");
        WebElement Account_click;
        Account_click = webActions.getWebElement(Account);
        ExtentFactory.getInstance().getExtent().pass("Clicking Signin", MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
        webActions.clickButton(Account_click);
        Log.info("Account button is clicked");
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Clicked Siginin Button");
    }


    public void enter_email(String email) throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement Email = webActions.getWebElement(Email_Text);
        Log.info("Getting email input element");
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Enter Email");
        Log.info("Entering email");
        webActions.sendKeysOnWebElement(Email, email);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Email Entered");
        ExtentFactory.getInstance().getExtent().pass("Email entered", MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
        WebElement Continue_click = webActions.getWebElement(Continue);
        Log.info("Clicking on continue");
        webActions.clickButton(Continue_click);
        Log.info("Clicked on continue");
    }

    public void enter_password(String password) throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement enter_password = webActions.getWebElement(Password);
        Log.info("Getting email input element");
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Enter password");
        Log.info("Entering password");
        webActions.sendKeysOnWebElement(enter_password, password);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Password Entered");
        ExtentFactory.getInstance().getExtent().pass("Password entered", MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
        WebElement click_signin = webActions.getWebElement(signin);
        Log.info("Clicking Sign in button");
        webActions.clickButton(click_signin);
        Log.info("Sign in Button is clicked");
    }

    public void verify_username_on_page_after_sign_in(String username) throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        String text = webActions.getWebElement(title_after_signin).getText();
        Log.info("Verified username on page after sign in");
        Assert.assertEquals(text,username);
    }
    public void verify_homepage() {
        String ActualTitle = driver.getTitle();
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Getting page title");
        String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Verified Page title");
    }
}
