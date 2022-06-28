package Utilities;


import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
    private ExtentFactory() {

    }

    private static ExtentFactory instance = new ExtentFactory();

    public static ExtentFactory getInstance() {
        return instance;
    }

    public static ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();

    public ExtentTest getExtent() {
        return extent.get();
    }

    public void setExtent(ExtentTest extentTestobject) {
        extent.set(extentTestobject);
    }
}

