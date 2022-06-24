package ui_tests;

import Utilities.CommonWebActions;
import Utilities.DriverFactory;
import Utilities.ExtentFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class LoginPage extends BaseClass {


    @Test
    public void Test_Login() throws InterruptedException, IOException {
        CommonWebActions web = new CommonWebActions(DriverFactory.getDriver());
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("prakruthi.koteshwar@gmail.com");
        WebElement element= web.getWebElement(By.xpath("//input[@id='continue']"));
        web.clickButton(element);
        DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("Prakruthi123!");
        DriverFactory.getDriver().findElement(By.xpath("//input[@id=\"signInSubmit\"]")).click();
       WebElement webElement = DriverFactory.getDriver().findElement(By.xpath("//select[@id='searchDropdownBox']"));
        web.clickElement(webElement);
       web.selectByVisibleText(webElement,"Beauty");
       WebElement elementMouseHover = webElement.findElement(By.xpath("//span[text()='Account & Lists']"));
       web.MouseOver(elementMouseHover);
       WebElement scrollIntoViewEle = webElement.findElement(By.xpath("//h2[text()='More items to explore']"));
       web.scrollIntoView(scrollIntoViewEle);
        ExtentFactory.getInstance().getExtent().pass("value entered", MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenShotAsBase64()).build());
    }

}