package Utilities;

import org.apache.commons.io.FileUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ExtentSetup  {
    static ExtentReports extent;
    public static ExtentReports setupExtentReport(){
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyy HH-mm-ss");
        Date date=new Date();
        String actualDate = format.format(date);
        String reportpath = System.getProperty("user.dir")+"/Reports/ExecutionReport_"+actualDate+".html";
        ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportpath);
        extent = new ExtentReports();
        extent.attachReporter(sparkReport);
        final File CONF = new File("extentconfig.xml");
        sparkReport.loadXMLConfig(String.valueOf(CONF));
        return extent;
    }
}
