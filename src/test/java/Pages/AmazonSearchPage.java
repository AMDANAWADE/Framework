package Pages;

import Utilities.BaseClass;
import Utilities.CommonWebActions;
import Utilities.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonSearchPage extends BaseClass {

    private WebDriver driver;

    public AmazonSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    By search_btn = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    By amazonProductLink = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span");
    By add_to_cart_btn = By.xpath("//*[@id='add-to-cart-button']");
    By cart_btn = By.xpath("//*[@id=\"nav-cart-count-container\"]/span[2]");
    By proceed_to_buy_btn = By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input");

    public void search_product(String searchinput) throws IOException {
        Log.info("Entering search product name");
        driver.findElement(searchBox).sendKeys(searchinput);
        Log.info("Entered search product name");
        driver.findElement(search_btn).click();
        Log.info("Cliked search button");
        Log.info("Clicking on product");
        driver.findElement(amazonProductLink).click();
        Log.info("Clicked search button");
        report_log(true, "Selected product");
    }

    public void add_to_cart() throws InterruptedException {
        CommonWebActions webActions = new CommonWebActions(driver);
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        report_log(true, "Adding product to cart");
        List<WebElement> addtoCartButton = driver.findElements(add_to_cart_btn);
        if ((addtoCartButton.size()) == 0) {
        } else {
            driver.findElement(add_to_cart_btn).click();
            webActions.wait(3000);
            Log.info("Clicking on add to cart button");
            Log.info("Clicked on add to cart button");
            WebElement cartButton = driver.findElement(cart_btn);
            webActions.clickElement(cartButton);
            Log.info("Clicked on cart button");
        }
        report_log(true, "Added product to cart");
    }

    public void proceed_to_buy() {
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        List<WebElement> proceedToBuyButton = driver.findElements(proceed_to_buy_btn);
        if ((proceedToBuyButton.size()) == 0) {
        } else {
            Log.info("Clicking on proceed to buy");
            report_log(true, "Proceeding to buy");
            driver.findElement(proceed_to_buy_btn).click();
            Log.info("Clicked on proceed to buy");
        }
        Log.info("Getting page title");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Amazon Sign In");
        Log.info("Verified page title");
        report_log(true, "Verified Page title");
    }
}


