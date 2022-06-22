package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonWebActions {

    private WebDriver driver;

    public CommonWebActions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButton(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        WebElement elements = null;
        elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", elements);
    }

    public void clickElement(WebElement element) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", element);
    }

    public void javascriptExecutor(By guide) {
        JavascriptExecutor executor = (JavascriptExecutor) this.driver;
        executor.executeScript("arguments[0].click();", this.driver.findElement(guide));
    }

    public WebElement getWebElement(By locator) {
        return this.driver.findElement(locator);
    }

    public void sendKeysOnWebElement(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void MouseOver(WebElement element) {
        Actions actObj = new Actions(this.driver);
        actObj.moveToElement(element).build().perform();
    }

    public static void selectByIndex(WebElement element, int indexValue) {
        Select selObj = new Select(element);
        selObj.selectByIndex(indexValue);
    }

    public static void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public static void selectByValue(WebElement element, String Value) {
        Select selectObject = new Select(element);
        selectObject.selectByValue(Value);
    }

    public String getCurrentWindowId() {
        return this.driver.getWindowHandle();
    }

    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void maximizePage() {
        this.driver.manage().window().maximize();
    }

    //To delete all cookies
    public void deleteAllCookies() {
        this.driver.manage().deleteAllCookies();
    }

    public void refreshPage() {
        this.driver.navigate().refresh();
    }

    public void navigateBack() {
        this.driver.navigate().back();
    }

    public void implicitWait(int seconds) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public void scrollUp() {
        ((JavascriptExecutor) this.driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    public void scrollDown() {
        ((JavascriptExecutor) this.driver).executeScript("window.scrollTo(0, document.body.scrollHeight");
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void wait(int timeInSeconds) throws InterruptedException {
        Thread.sleep(timeInSeconds);
    }

    public void scrollBy(int x,int y)
    {
        ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(x,y)", "");
    }

}
