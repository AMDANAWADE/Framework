package Pages;

import Utilities.CommonWebActions;
import Utilities.ExtentFactory;
import Utilities.ListenersImplementation;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import ui_tests.BaseClass;

import java.io.IOException;

@Listeners(ListenersImplementation.class)
public class LoginPage {
    private WebDriver driver;
    By Account = By.xpath("//a[@id='nav-link-accountList']");
    By Email_Text = By.xpath("//input[@id='ap_email']");
    By Continue = By.xpath("//input[@id='continue']");
    By Password = By.xpath("//input[@type='password']");
    By signin = By.id("signInSubmit");
    static Logger log = LogManager.getLogger(LoginPage.class);


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        BaseClass baseClass = new BaseClass();
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Click Signin Button");
        WebElement Account_click;
        Account_click = webActions.getWebElement(Account);
        ExtentFactory.getInstance().getExtent().pass("Clicking Signin", MediaEntityBuilder.createScreenCaptureFromBase64String(baseClass.getScreenShotAsBase64(driver)).build());
        webActions.clickButton(Account_click);
        log.info("Account button is clicked");
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Clicked Siginin Button");
    }


    public void enter_email() throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        BaseClass baseClass = new BaseClass();
        WebElement Email = webActions.getWebElement(Email_Text);
        log.info("Getting email input element");
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Enter Email");
        log.info("Entering email");
        webActions.sendKeysOnWebElement(Email,"amrutadanawade18@gmail.com");
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Email Entered");
        ExtentFactory.getInstance().getExtent().pass("Email entered", MediaEntityBuilder.createScreenCaptureFromBase64String(baseClass.getScreenShotAsBase64(driver)).build());
        WebElement Continue_click = webActions.getWebElement(Continue);
        log.info("Clicking on continue");
        webActions.clickButton(Continue_click);
        log.info("Clicked on continue");
    }

    public void enter_password() throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        BaseClass baseClass = new BaseClass();
        WebElement enter_password = webActions.getWebElement(Password);
        log.info("Getting email input element");
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Enter password");
        log.info("Entering password");
        webActions.sendKeysOnWebElement(enter_password,"Amruta_18;");
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Password Entered");
        ExtentFactory.getInstance().getExtent().pass("Password entered", MediaEntityBuilder.createScreenCaptureFromBase64String(baseClass.getScreenShotAsBase64(driver)).build());
        WebElement click_signin = webActions.getWebElement(signin);
        log.info("Clicking Signin button");
        webActions.clickButton(click_signin);
        log.info("Signin Button is clicked");
    }

    public void verify_homepage() {
        String ActualTitle = driver.getTitle();
        ExtentFactory.getInstance().getExtent().log(Status.INFO, "Getting page title");
        String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Verified Page title");
    }
}
