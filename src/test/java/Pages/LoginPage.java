package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class LoginPage extends BaseClass {

    private WebDriver driver;
    By Account = By.xpath("//a[@id='nav-link-accountList']");
    By Email_Text = By.xpath("//input[@id='ap_email']");
    By Continue = By.xpath("//input[@id='continue']");
    By Password = By.xpath("//input[@type='password']");
    By signin = By.id("signInSubmit");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        CommonWebActions webActions = new CommonWebActions(driver);
        report_log(true, "Click Signin Button");
        WebElement Account_click;
        Account_click = webActions.getWebElement(Account);
        report_log(true, "Clicked on Sign in");
        webActions.clickButton(Account_click);
        Log.info("Account button is clicked");
        report_log(true, "Clicked Siginin Button");
    }

    public void enter_email(String email) {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement Email = webActions.getWebElement(Email_Text);
        Log.info("Getting email input element");
        Log.info("Entering email");
        webActions.sendKeysOnWebElement(Email, email);
        report_log(true, "Email entered");
        WebElement Continue_click = webActions.getWebElement(Continue);
        Log.info("Clicking on continue");
        webActions.clickButton(Continue_click);
        Log.info("Clicked on continue");
    }

    public void enter_password(String password) {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement enter_password = webActions.getWebElement(Password);
        Log.info("Getting email input element");
        Log.info("Entering password");
        webActions.sendKeysOnWebElement(enter_password, password);
        report_log(true, "Password Entered");
        WebElement click_signin = webActions.getWebElement(signin);
        Log.info("Clicking Sign in button");
        webActions.clickButton(click_signin);
        Log.info("Sign in Button is clicked");
    }

    public void verify_homepage() {
        String ActualTitle = driver.getTitle();
        report_log(true, "Getting page title");
        String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
        report_log(true, "Verified page title");
    }
}
