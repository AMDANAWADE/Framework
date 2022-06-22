package ui_tests;

import Utilities.CommonWebActions;
import Utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginPage extends BaseClass {

    @Test
    public void Test_Login() throws InterruptedException {
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

    }
}
//        @Test
//        public void Login2(){
//            DriverFactory.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("prakruthi.koteshwar@gmail.com");
//            DriverFactory.getDriver().findElement(By.xpath("//input[@id='continue']")).click();
//            DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("Prakruthi123!");
//            DriverFactory.getDriver().findElement(By.xpath("//input[@id=\"signInSubmit\"]")).click();
//
//        @Test
//        public void Login3(){
//            DriverFactory.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("prakruthi.koteshwar@gmail.com");
//            DriverFactory.getDriver().findElement(By.xpath("//input[@id='continue']")).click();
//            DriverFactory.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys("Prakruthi123!");
//            DriverFactory.getDriver().findElement(By.xpath("//input[@id=\"signInSubmit\"]")).click();
//
//      }
//    }
