package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class CommonWebActions {

    private WebDriver driver;
    BaseClass baseClass = new BaseClass();
    public CommonWebActions(WebDriver driver) {
        this.driver = driver;
    }

    /***
     *This method is to click a button after waiting till the element is to be clickable.
     * The button is clicked using javascript executor.
     * @param element is web element
     */
    public void clickButton(WebElement element) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
            WebElement elements = null;
            elements = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", elements);
            baseClass.report_log(true, "Cliked on button " + element);
        } catch (Exception e) {
            baseClass.report_log(false, "Cannot click the button " + element);
        }
    }
    /***
     * This method is to click a web element
     * @param element is web element
     */
    public void clickElement(WebElement element) {
        try {
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].click();", element);
            baseClass.report_log(true, "Clicked on web element " + element);
        } catch (Exception e) {
            baseClass.report_log(true, "Cannot click the element " +e.getMessage());
        }
    }
    /***
     * This method is to click a web element using javascript executor
     * @param guide is xpath locator
     */
    public void javascriptExecutor(By guide) {
        JavascriptExecutor executor = (JavascriptExecutor) this.driver;
        executor.executeScript("arguments[0].click();", this.driver.findElement(guide));
    }

    /***
     * This method returns web element when xpath is given as parameter
     * @param locator is xpath locator
     * @return web element
     */
    public WebElement getWebElement(By locator) {
        WebElement element = null;
        try {
            element = this.driver.findElement(locator);
            baseClass.report_log(true, "Getting web element " + locator);

        } catch (Exception e) {
            baseClass.report_log(false, "Could not get element " +e.getMessage());
        }
        return element;
    }

    /***
     * This method is to send keys for the web element
     * @param element is web element
     * @param text is input string
     */
    public void sendKeysOnWebElement(WebElement element, String text) {
        try {
            element.click();
            element.clear();
            element.sendKeys(text);
            baseClass.report_log(true, "Entering input data " + text);
        } catch (Exception e) {
            baseClass.report_log(false, "Cannot enter input data " + e.getMessage());
        }
    }
    /***
     * This method is to select a web element from dropdown
     * @param element is web element
     * @param text is visible text
     */
    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    /***
     * This method is to perform mouse hover action on the web element
     * @param element is web element
     */
    public void MouseOver(WebElement element) {
        try {
            Actions actObj = new Actions(this.driver);
            actObj.moveToElement(element).build().perform();
            baseClass.report_log(true, "Mouse hover on the element " + element);
        }
        catch (Exception e)
        {
            baseClass.report_log(false,"Cannot hover on the element "+e.getMessage());
        }
    }

    /***
     * This method is to select an option from dropdown by index value
     * @param element is the web element
     * @param indexValue is index value
     */
    public void selectByIndex(WebElement element, int indexValue) {
        Select selObj = new Select(element);
        selObj.selectByIndex(indexValue);
    }


    /***
     * This method is to accept alert
     */
    public void acceptAlert() {
        this.driver.switchTo().alert().accept();
    }

    /***
     * This method is to select an option from dropdown by passing value
     * @param element is webelement
     * @param Value the value of the web element to be selected
     */
    public void selectByValue(WebElement element, String Value) {
        Select selectObject = new Select(element);
        selectObject.selectByValue(Value);
    }

    /***
     * This method is to get window ID
     * @return returns the window ID
     */
    public String getCurrentWindowId() {
        return this.driver.getWindowHandle();
    }

    /***
     * This method is to verify if element is present or not
     * @param by is the locator
     * @return returns true if element is present
     */
    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * This method is to maximize the current window
     */
    public void maximizePage() {
        this.driver.manage().window().maximize();
    }

    /***
     * This method is to delete all cookies
     */
    //To delete all cookies
    public void deleteAllCookies() {
        this.driver.manage().deleteAllCookies();
    }

    /***
     * This method is to refresh a page
     */
    public void refreshPage() {
        this.driver.navigate().refresh();
    }

    /***
     * This method is to navigate back
     */
    public void navigateBack() {
        this.driver.navigate().back();
    }

    /***
     * This method is to wait for specified amount of time
     * @param seconds is the duration
     */
    public void implicitWait(int seconds) {
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /***
     * This method is to scroll up the window
     */
    public void scrollUp() {
        ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(document.body.scrollHeight, 0)");
    }

    /***
     * This method is to scroll down the window
     */
    public void scrollDown() {
        ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    /***
     * This method is to scroll into a view
     * @param element is web element
     */
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /***
     * This method is to wait for given time
     * @param timeInSeconds time in seconds
     */
    public void wait(int timeInSeconds) throws InterruptedException {
        Thread.sleep(timeInSeconds);
    }

    /***
     * This is to scroll window to the given dimensions
     * @param x is the value to scroll horizontally
     * @param y is the value to scroll vertically
     */
    public void scrollBy(int x, int y) {
        ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(x,y)");
    }

    /***
     * This method is to take screenshot as base64 string
     * @return returns the screenshot as string
     */
    public String getScreenShotAsBase64() {
        String source = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BASE64);
        String base64img = "data:image/jpeg;base64, ";
        return base64img + source;
    }
}
