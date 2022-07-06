package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.*;


public class ListenersImplementation extends BaseClass implements ITestListener, ISuiteListener {
    static ExtentReports report;
    ExtentTest test;
    PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");

    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
    }

    public void onTestFailure(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().fail("Test Case " + Result.getMethod().getMethodName() + " is failed");
    }

    public void onTestSkipped(ITestResult Result) {
    }


    public void onTestStart(ITestResult Result) {
        test = report.createTest(Result.getMethod().getMethodName());
        ExtentFactory.setExtent(test);
    }

    public void onTestSuccess(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().pass("Test Case " + Result.getMethod().getMethodName() + " is passed");
    }


    public void onFinish(ITestContext context) {
        report.flush();
    }

    public void onStart(ISuite iSuite)
    {
        report = ExtentSetup.setupExtentReport();
    }
}

