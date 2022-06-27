package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    private DriverFactory() {

    }

    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static String projectPath = System.getProperty("user.dir");
    public static PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
    public static String use_internal_drivers = prop.getProperty("USE_INTERNAL_DRIVERS");

    public static void setDriver(String browserName) {
        WebDriver driverName;
        switch (browserName.toUpperCase()) {
            case "FIREFOX":
                driverName = getFirefoxDriver();
                break;
            case "EDGE":
                driverName = getEdgeDriver();
                break;
            case "IE":
                driverName = getIEDriver();
                break;
            default:
                driverName = getChromeDriver();
        }
        driverThreadLocal.set(driverName);
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static WebDriver getFirefoxDriver() {
        if (use_internal_drivers.equalsIgnoreCase("yes"))
            System.setProperty("webdriver.gecko.driver", projectPath + "/drivers/geckodriver.exe");
        else
            WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    public static WebDriver getChromeDriver() {
        if (use_internal_drivers.equalsIgnoreCase("yes"))
            System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver.exe");
        else
            WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static WebDriver getEdgeDriver() {
        if (use_internal_drivers.equalsIgnoreCase("yes"))
            System.setProperty("webdriver.edge.driver", projectPath + "/drivers/msedgedriver.exe");
        else
            WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    public static WebDriver getIEDriver() {
        if (use_internal_drivers.equalsIgnoreCase("yes"))
            System.setProperty("webdriver.ie.driver", projectPath + "/drivers//IEDriverServer.exe");
        else
            WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver();
    }
}
