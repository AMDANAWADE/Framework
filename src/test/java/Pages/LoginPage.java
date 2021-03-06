package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


public class LoginPage extends BaseClass {

    private WebDriver driver;
    private CommonWebActions webActions;
    By Account = By.xpath("//a[@id='nav-link-accountList']");
    By Email_Text = By.xpath("//input[@id='ap_email']");
    By Continue = By.xpath("//input[@id='continue']");
    By Password = By.xpath("//input[@type='password']");
    By signin = By.id("signInSubmit");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        webActions = new CommonWebActions(this.driver);
    }


    public void login() {
        report_log(true, "Click on Sign in Button");
        WebElement Account_click;
        Account_click = webActions.getWebElement(Account);
        webActions.clickElement(Account_click);
        report_log(true, "Clicked on Sign in");
    }

    public void enter_email(String email) {
        WebElement Email = webActions.getWebElement(Email_Text);
        webActions.sendKeysOnWebElement(Email, email);
        report_log(true, "Email entered");
        WebElement Continue_click = webActions.getWebElement(Continue);
        webActions.clickButton(Continue_click);
        report_log(true, "Clicked on continue");
    }

    public void enter_password(String password) {
        WebElement enter_password = webActions.getWebElement(Password);
        webActions.sendKeysOnWebElement(enter_password, password);
        report_log(true, "Password Entered");
        WebElement click_signin = webActions.getWebElement(signin);
        webActions.clickButton(click_signin);
        report_log(true, "Clicked on Sign in");
    }

    public void verify_homepage() {
        String ActualTitle = driver.getTitle();
        report_log(true, "Getting page title");
        String ExpectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
        report_log(true, "Verified page title");
    }
}
