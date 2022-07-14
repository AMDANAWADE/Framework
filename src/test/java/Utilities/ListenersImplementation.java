package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;


public class ListenersImplementation extends BaseClass implements ITestListener, ISuiteListener {
    static ExtentReports report;
    ExtentTest test;

    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
    }

    public void onTestFailure(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().fail("Test Case " + Result.getMethod().getMethodName() + " is failed");
        ITestContext context = Result.getTestContext();
        ExtentFactory.getInstance().getExtent().assignCategory(context.getCurrentXmlTest().getName());
    }

    public void onTestSkipped(ITestResult Result) {
    }


    public void onTestStart(ITestResult Result) {
        test = report.createTest(Result.getMethod().getDescription());
        ExtentFactory.setExtent(test);
    }

    public void onTestSuccess(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().pass("Test Case " + Result.getMethod().getMethodName() + " is passed");
        ITestContext context = Result.getTestContext();
        ExtentFactory.getInstance().getExtent().assignCategory(context.getCurrentXmlTest().getName());
    }


    public void onFinish(ITestContext context) {
        report.flush();
    }

    public void onStart(ISuite iSuite) {
        report = ExtentSetup.setupExtentReport();

    }
}

