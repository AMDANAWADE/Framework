package Pages;

import Utilities.CommonWebActions;
import Utilities.ExtentFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ui_tests.BaseClass;

import java.io.IOException;


public class LoginPage {
    private WebDriver driver;
    By Account = By.xpath("//a[@id='nav-link-accountList']");
    By Email_Text = By.xpath("//input[@id='ap_email']");
    By Continue = By.xpath("//input[@id='continue']");
    By Password = By.xpath("//input[@type='password']");
    By signin = By.id("signInSubmit");
    BaseClass baseClass = new BaseClass();
    static Logger log = LogManager.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() throws IOException {
        CommonWebActions webActions = new CommonWebActions(driver);
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "click Signin Button");
        WebElement Account_click;
        log.info("Clicking account button");
        Account_click = webActions.getWebElement(Account);
        webActions.clickButton(Account_click);
        log.info("Account button is clicked");
        ExtentFactory.getInstance().getExtent().pass("value entered", MediaEntityBuilder.createScreenCaptureFromBase64String(baseClass.getScreenShotAsBase64(driver)).build());
    }


    public void enter_email() {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement Email = webActions.getWebElement(Email_Text);
        log.info("Clicking account button");
        webActions.sendKeysOnWebElement(Email,"amrutadanawade18@gmail.com");
        WebElement Continue_click = webActions.getWebElement(Continue);
        log.info("Clicking account button");
        webActions.clickButton(Continue_click);

    }

    public void enter_password() {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement enter_password = webActions.getWebElement(Password);
        log.info("Clicking account button");
        webActions.sendKeysOnWebElement(enter_password,"Amruta_18;");
        WebElement click_signin = webActions.getWebElement(signin);
        webActions.clickButton(click_signin);
        log.info("Clicking account button");
    }

    public void verify_homepage() {
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
    }
}
