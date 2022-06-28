package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AmazonSearchPage {

    private WebDriver driver;

    public AmazonSearchPage(WebDriver driver) {
        this.driver = driver;
    }
    By searchBox = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    By search_btn = By.xpath("//*[@id=\"nav-search-submit-button\"]");
    By amazonProductLink = By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[3]/div/div/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span");

    public void searchProduct(String searchinput) {
        driver.findElement(searchBox).sendKeys(searchinput);
        driver.findElement(search_btn).click();
        driver.findElement(amazonProductLink).click();
    }

    public void AddtoCart() throws InterruptedException {

        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        List<WebElement> addtoCartButton = driver.findElements(By.xpath("//*[@id=\"add-to-cart-button\"]"));
        if (addtoCartButton.size() == 0) {
            //do something
        } else {
            driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
            Thread.sleep(3000);

            WebElement cartButton = driver.findElement(By.xpath("//*[@id=\"nav-cart-count-container\"]/span[2]"));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", cartButton);
        }

    }

    public void proceedToBuy() {
        List<String> windows = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        System.out.println(driver.getTitle());
        List<WebElement> proceedToBuyButton = driver.findElements(By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input"));
        if (proceedToBuyButton.size() == 0) {
            //do something
        } else {
            driver.findElement(By.xpath("//*[@id=\"sc-buy-box-ptc-button\"]/span/input")).click();
        }

        String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title,"Amazon Sign In");
    }
}


