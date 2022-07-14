package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchPage extends BaseClass {

    private WebDriver driver;
    private CommonWebActions webActions;
    public AmazonSearchPage(WebDriver driver) {
        this.driver = driver;
        webActions = new CommonWebActions(this.driver);
    }
    By searchBox = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    By search_btn = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    By amazonProductLink = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span");
    By add_to_cart_btn = By.xpath("//*[@id='add-to-cart-button']");
    By added_to_cart_title = By.xpath("//span[contains(text(),'Added to Cart')]");
    By proceed_to_buy_btn = By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input");
    By cart_button = By.xpath("//*[@id=\"nav-cart-count-container\"]/span[2]");

    public void search_product(String searchinput)  {
        WebElement search_box = webActions.getWebElement(searchBox);
        webActions.sendKeysOnWebElement(search_box, searchinput);
        report_log(true, "Entered search product name");
        WebElement search_button = webActions.getWebElement(search_btn);
        webActions.clickElement(search_button);
        driver.findElement(amazonProductLink).click();
        report_log(true, "Selected product");
    }

    public void add_to_cart() {
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        report_log(true, "Adding product to cart");
        List<WebElement> addtoCartButton = driver.findElements(add_to_cart_btn);
        if ((addtoCartButton.size()) == 0) {
        } else {
            WebElement add_to_cart = webActions.waitTillElementIsVisible(add_to_cart_btn);
            webActions.clickElement(add_to_cart);
            webActions.waitTillElementIsVisible(added_to_cart_title);
            WebElement cartButton = webActions.waitTillElementIsVisible(cart_button);
            webActions.clickElement(cartButton);
        }
        report_log(true, "Added product to cart");
    }

    public void proceed_to_buy() {
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        List<WebElement> proceedToBuyButton = driver.findElements(proceed_to_buy_btn);
        if ((proceedToBuyButton.size()) == 0) {
        } else {
            report_log(true, "Proceeding to buy");
            WebElement proceedToBuy = webActions.getWebElement(proceed_to_buy_btn);
            webActions.clickElement(proceedToBuy);
            report_log(true, "Clicked on proceeding to buy");
        }
        String title = driver.getTitle();
        Assert.assertEquals(title, "Amazon Sign In");
        report_log(true, "Verified Page title");
    }
}


