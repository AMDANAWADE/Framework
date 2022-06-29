package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.ThreadContext;
import org.testng.*;
import ui_tests.BaseClass;


public class ListenersImplementation extends BaseClass implements ITestListener, ISuiteListener {
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
    }


    public void onTestFailure(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case" + Result.getMethod().getMethodName() + "is Fail");
        ExtentFactory.getInstance().getExtent().log(Status.FAIL, Result.getThrowable());
    }


    public void onTestSkipped(ITestResult Result) {
    }


    public void onTestStart(ITestResult Result) {
        test = report.createTest(Result.getMethod().getMethodName());
        ExtentFactory.getInstance().setExtent(test);
    }

    public void onTestSuccess(ITestResult Result) {
        ExtentFactory.getInstance().getExtent().log(Status.PASS, "Test Case" + Result.getMethod().getMethodName() + "is passed");
    }


    public void onFinish(ITestContext context) {
        report.flush();
    }

    public void onStart(ISuite iSuite) {
        report = ExtentSetup.setupExtentReport();
        ThreadContext.put("ClassName",iSuite.getName()+System.currentTimeMillis()+".txt");
    }

}

