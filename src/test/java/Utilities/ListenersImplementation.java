package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.*;


public class ListenersImplementation implements ITestListener, ISuiteListener {
    static ExtentReports report;
    ExtentTest test;
    PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");

    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
    }


    public void onTestFailure(ITestResult Result) {
        CommonWebActions webActions = new CommonWebActions(DriverFactory.getDriver());
        String step_screenshot_flag = prop.getProperty("STEP_SCREENSHOT");
        try {
            if (step_screenshot_flag.equalsIgnoreCase("yes"))
                ExtentFactory.getInstance().getExtent().fail("Test Case " + Result.getMethod().getMethodName() + " is failed", MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
            else
                ExtentFactory.getInstance().getExtent().fail("Test Case " + Result.getMethod().getMethodName() + " is failed");
        } catch (Exception e) {
            Log.info("Unable to add test step");
        }

    }


    public void onTestSkipped(ITestResult Result) {
    }


    public void onTestStart(ITestResult Result) {
        test = report.createTest(Result.getMethod().getMethodName());
        ExtentFactory.getInstance().setExtent(test);

    }

    public void onTestSuccess(ITestResult Result) {
        CommonWebActions webActions = new CommonWebActions(DriverFactory.getDriver());
        String step_screenshot_flag = prop.getProperty("STEP_SCREENSHOT");
        try {
            if (step_screenshot_flag.equalsIgnoreCase("yes"))
                ExtentFactory.getInstance().getExtent().pass("Test Case " + Result.getMethod().getMethodName() + " is passed", MediaEntityBuilder.createScreenCaptureFromBase64String(webActions.getScreenShotAsBase64()).build());
            else
                ExtentFactory.getInstance().getExtent().pass("Test Case " + Result.getMethod().getMethodName() + " is passed");
        } catch (Exception e) {
            Log.info("Unable to add test step");
        }
    }


    public void onFinish(ITestContext context) {
        report.flush();
    }

    public void onStart(ISuite iSuite) {
        report = ExtentSetup.setupExtentReport();
    }

}

