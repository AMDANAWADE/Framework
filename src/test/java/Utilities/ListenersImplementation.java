package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ListenersImplementation implements ITestListener, ISuiteListener {
    static ExtentReports report;
    ExtentTest test;


    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
    }


    public void onTestFailure(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case" + Result.getMethod().getMethodName() + "is Fail");
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, Result.getThrowable());
    }


    public void onTestSkipped(ITestResult Result) {
    }


    public void onTestStart(ITestResult Result) {
        test = report.createTest(Result.getMethod().getMethodName()).assignCategory("regression ").assignDevice("Chrome");
        ExtentFactory.getInstance().setExtent(test);
    }


    public void onTestSuccess(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case" + Result.getMethod().getMethodName() + "is passed");
    }


    public void onFinish(ISuite suite) {
        report.flush();
    }
    public void onStart(ISuite suite) {
        report = ExtentSetup.setupExtentReport();
    }
}

