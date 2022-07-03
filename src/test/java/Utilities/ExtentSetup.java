package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentSetup {
    static ExtentReports extent;
    public static ExtentReports setupExtentReport() {

        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);
        String fileName = System.getProperty("user.dir") + "/Reports/ExecutionReport_" + actualDate + ".html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(new File(fileName));
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;

    }


}
