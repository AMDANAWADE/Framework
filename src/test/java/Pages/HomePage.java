package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends BaseClass {

    public WebDriver driver;

    By Orders_title = By.xpath("//h1[text()='Your Orders']");
    By OrdersBtn = By.xpath("//span[contains(text(),'Returns')]");
    By ChangeLang = By.xpath("//span[@class='icp-nav-flag icp-nav-flag-in']");
    By LanguageSettingsTitle = By.xpath("//h3[contains(text(),\"Language Settings\")]");
    By HelpLink = By.xpath("//a[text()='Help']");
    By Help_Page_heading = By.xpath("//h1[contains(text(),\"What can we help you with\")]");
    By ContactUsTitle = By.xpath("//h1[text()='Want to chat now or get a call from us?']");
    By CustomerServiceMouseHover = By.xpath("//a[@rel='#help-gateway-category-12']");
    By ContactUsBtn = By.xpath("(//button[text()='Contact Us'])[1]");
    By ChangeCountry = By.xpath("//div[text()='Change country/region.']");
    By FlagMouseHover = By.xpath("//span[@class='icp-nav-flag icp-nav-flag-in']");
    By add_new_address_title = By.xpath("//h2[contains(text(),\"new address\")]");
    By YourAccount = By.xpath("//span[text()='Your Account']");
    By YourAddresses = By.xpath("(//h2[@class='a-spacing-none ya-card__heading--rich a-text-normal'])[4]");
    By AddAddress = By.xpath("//div[@class='a-box-inner a-padding-extra-large']");
    By AccountMouseHover = By.xpath("//span[@class='nav-line-2 ']");
    By Menu = By.xpath("(//span[text()='All'])[2]");
    By NewReleases = By.xpath("(//a[text()='New Releases'])[2]");
    By NewReleasesPageTitle = By.xpath("//span[text()='Amazon Hot New Releases']");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void click_orders() throws IOException, InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        report_log(true, "Click on orders");
        WebElement orders = webActions.getWebElement(OrdersBtn);
        webActions.clickElement(orders);
        webActions.wait(1000);
        Assert.assertEquals(driver.findElement(Orders_title).getText(), "Your Orders");
        report_log(true, "Opened order page and verified page order title");
    }

    public void click_on_language_options() {
        CommonWebActions webActions = new CommonWebActions(driver);
        report_log(true, "Click on select languages");
        WebElement lang = webActions.getWebElement(ChangeLang);
        webActions.clickElement(lang);
        Assert.assertEquals(driver.findElement(LanguageSettingsTitle).getText(), "Language Settings");
        report_log(true, "Clicked on select languages and verified page title");
    }

    public void click_help_link() throws InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        webActions.scrollDown();
        webActions.wait(3000);
        WebElement help = webActions.getWebElement(HelpLink);
        help.click();
        report_log(true, "Clicked on help link");
        boolean value = webActions.getWebElement(Help_Page_heading).getText().contains("What can we help you with");
        Assert.assertEquals(value, true);
        report_log(true, "Verified help page title");
    }

    public void verify_contact_us_page(){
        CommonWebActions webActions = new CommonWebActions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0,300)");
        }
        WebElement customerservice = webActions.getWebElement(CustomerServiceMouseHover);
        webActions.MouseOver(customerservice);
        WebElement contactUs = webActions.getWebElement(ContactUsBtn);
        contactUs.click();
        report_log(true, "Clicked on contact us button");
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        Assert.assertEquals(driver.findElement(ContactUsTitle).getText(), "Want to chat now or get a call from us?");
        report_log(true, "Verified the contact page title");

    }

    public void open_change_country_settings() throws InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement FlagElement = webActions.getWebElement(FlagMouseHover);
        webActions.MouseOver(FlagElement);
        webActions.wait(2000);
        report_log(true, "Opened change country settings");
        WebElement change_country = webActions.getWebElement(ChangeCountry);
        webActions.clickElement(change_country);
        report_log(true, "Verified change country settings title");
    }

    public void click_your_account() throws InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement AccountElement = webActions.getWebElement(AccountMouseHover);
        webActions.MouseOver(AccountElement);
        WebElement your_account = webActions.getWebElement(YourAccount);
        webActions.clickButton(your_account);
        webActions.wait(2000);
        report_log(true, "Clicked on your account");
    }

    public void click_your_addresses() throws InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement your_addresses = webActions.getWebElement(YourAddresses);
        webActions.clickButton(your_addresses);
        webActions.wait(2000);
        report_log(true, "Clicked on your addresses");
    }

    public void add_address() {
        WebElement add_address = driver.findElement(AddAddress);
        add_address.click();
        boolean value = driver.findElement(add_new_address_title).getText().contains("new address");
        Assert.assertEquals(value, true);
        report_log(true, "Verified title to add new address");
    }
    public void shop_new_releases() throws InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        WebElement all= driver.findElement(Menu);
        all.click();
        driver.findElement(NewReleases).click();
        webActions.wait(1000);
        String title = webActions.getText(NewReleasesPageTitle);
        Assert.assertEquals(title,"Amazon Hot New Releases");
        report_log(true, "Verified new releases page title");
    }
}
