package Utilities;


import com.aventstack.extentreports.ExtentTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentFactory {
    private ExtentFactory() {

    }

    private static ExtentFactory instance = new ExtentFactory();

    public static ExtentFactory getInstance() {
        return instance;
    }

    ThreadLocal<ExtentTest> extent = new ThreadLocal<ExtentTest>();

    public ExtentTest getExtent() {
        return extent.get();
    }

    public void setExtent(ExtentTest extentTestobject) {
        extent.set(extentTestobject);
    }

    public static String getFilename()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);
        String filename = System.getProperty("user.dir") + "/Reports/ExecutionReport_" + actualDate + ".html";

        return filename;
    }
}

