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
            baseClass.report_log(true, "Clicked on button " + element);
        } catch (Exception e) {
            Log.info(e.getMessage());
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
            Log.info(e.getMessage());
            baseClass.report_log(false, "Cannot click the element "+ element);
        }
    }
    /***
     * This method is to click a web element using javascript executor
     * @param guide is xpath locator
     */
    public void clickElement(By guide) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) this.driver;
            executor.executeScript("arguments[0].click();", this.driver.findElement(guide));
            baseClass.report_log(true,"Clicked on web element "+guide);
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot click the element "+guide);
        }
    }

    public String getText(By by)
    {
        String text ="";
        try{
            text = driver.findElement(by).getText();
            baseClass.report_log(true,"Getting text of the web element "+by);
        }
        catch (Exception e)
        {
            baseClass.report_log(true,"Cannot get the text from web element");
            Log.info(e.getMessage());
        }
        return text;
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
            Log.info(e.getMessage());
            baseClass.report_log(false, "Could not get element " +locator);
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
            Log.info(e.getMessage());
            baseClass.report_log(false, "Cannot enter input data " + text);
        }
    }
    /***
     * This method is to select a web element from dropdown
     * @param element is web element
     * @param text is visible text
     */
    public void selectByVisibleText(WebElement element, String text) {
        try {
            Select select = new Select(element);
            select.selectByVisibleText(text);
            baseClass.report_log(true, "Selected element by visible text " + element + text);
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false, "Cannot select element on visible text");
        }
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
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot hover on the element "+element);
        }
    }

    /***
     * This method is to select an option from dropdown by index value
     * @param element is the web element
     * @param indexValue is index value
     */
    public void selectByIndex(WebElement element, int indexValue) {
        try {
            Select selObj = new Select(element);
            selObj.selectByIndex(indexValue);
            baseClass.report_log(true, "Selected value by index");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false, "Cannot select element by index " + element + indexValue);
        }
    }

    /***
     * This method is to accept alert
     */
    public void acceptAlert() {
        this.driver.switchTo().alert().accept();
    }

    /***
     * This method is to select an option from dropdown by passing value
     * @param element is web element
     * @param Value the value of the web element to be selected
     */
    public void selectByValue(WebElement element, String Value) {
        try {
            Select selectObject = new Select(element);
            selectObject.selectByValue(Value);
            baseClass.report_log(true,"Selected an option based on value");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot select an option based on value");
        }
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
            WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            this.driver.findElement(by);
            baseClass.report_log(true,"Element is present "+by);
            return true;
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot find the element "+by);
            return false;
        }
    }

    /***
     * This method is to maximize the current window
     */
    public void maximizePage() {
        try {
            this.driver.manage().window().maximize();
            baseClass.report_log(true,"Maximizing the page");
        }
        catch (Exception e)
        {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot maximize the page");
        }

    }

    /***
     * This method is to delete all cookies
     */
    //To delete all cookies
    public void deleteAllCookies() {
        try {
            this.driver.manage().deleteAllCookies();
            baseClass.report_log(true, "Deleted all cookies");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false, "Cannot delete all cookies");
        }
    }
    /***
     * This method is to refresh a page
     */
    public void refreshPage() {
        try {
            this.driver.navigate().refresh();
            baseClass.report_log(true,"Refreshing the page");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot refresh the page");
        }
    }

    /***
     * This method is to navigate back
     */
    public void navigateBack() {
        try {
            this.driver.navigate().back();
            baseClass.report_log(true, "Navigating back");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false, "Cannot navigate back");
        }
    }
    /***
     * This method is to wait for specified amount of time
     * @param seconds is the duration
     */
    public void implicitWait(int seconds) {
        try {
            this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
            baseClass.report_log(true, "Waiting for duration of " + seconds + " seconds");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false, "Waiting interrupted");
        }
    }

    /***
     * This method is to scroll up the window
     */
    public void scrollUp() {
        try {
            ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(document.body.scrollHeight, 0)");
            baseClass.report_log(true,"Scroll up the window action is performed");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot perform scroll up action");
        }
    }

    /***
     * This method is to scroll down the window
     */
    public void scrollDown() {
        try {
            ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
            baseClass.report_log(true,"Scroll down the window action is performed");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot perform scroll down action");
        }
    }

    /***
     * This method is to scroll into a view
     * @param element is web element
     */
    public void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView();", element);
            baseClass.report_log(true,"Scrolled into the view "+element);

        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot perform scroll into view action for element "+element);

        }
    }
    /***
     * This method is to wait for given time
     * @param timeInSeconds time in seconds
     */
    public void wait(int timeInSeconds) throws InterruptedException {
        try {
            Thread.sleep(timeInSeconds);
            baseClass.report_log(true,"Waited for "+timeInSeconds+" seconds");
        } catch (Exception e) {
            Log.info(e.getMessage());
            baseClass.report_log(false,"Cannot perform wait action");
        }
    }
    /***
     * This is to scroll window to the given dimensions
     * @param x is the value to scroll horizontally
     * @param y is the value to scroll vertically
     */
    public void scrollBy(int x, int y) {
        try {
            ((JavascriptExecutor) this.driver).executeScript("window.scrollBy(x,y)");
            baseClass.report_log(true,"Scrolling by pixel value "+x+y);
        } catch (Exception e) {
            baseClass.report_log(true, "Cannot scroll the window by the given pixel value");
        }
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
