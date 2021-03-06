package Utilities;

import com.aventstack.extentreports.ExtentReports;
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
        ExtentSparkReporter sparkReport = new ExtentSparkReporter(fileName);
        final File CONF = new File("extentconfig.xml");
        sparkReport.loadXMLConfig(String.valueOf(CONF));
        extent = new ExtentReports();
        extent.attachReporter(sparkReport);
        return extent;
    }


}
