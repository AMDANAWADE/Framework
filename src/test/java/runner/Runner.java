package runner;

import Utilities.*;
import org.testng.TestNG;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlClass;

import java.util.*;

public class Runner {
    public static void main(String[] args) {
        PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
        String runnerFile = prop.getProperty("RUNNER_FILE_PATH");
        List<Map<String, String>> testcases = new LinkedList<>();
        if (runnerFile != null) {
            ExcelHandling excelHandlingRunner= new ExcelHandling(runnerFile, 0);
            int col_index = excelHandlingRunner.get_column_index_having_value(0, "EXECUTE");
            int row_index = excelHandlingRunner.get_row_index(col_index, "YES");
            List<Integer> row_indexes = excelHandlingRunner.get_row_indexes_having_value_in_column(row_index, col_index, "YES");
            testcases = excelHandlingRunner.getTestCases(row_indexes);
            if (testcases.size() < 1) {
                System.out.println("No Tests to run!");
                System.exit(0);
            }

        } else {
            System.out.println("Could not identify the runner file.");
            System.exit(0);
        }
        try {
            List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
            Set<String> classNames = new HashSet<>();
            Set<String> tc_methods = new HashSet<>();
            XmlSuite suite = new XmlSuite();
            suite.setName("ProgramSuite");
            XmlTest test = new XmlTest(suite);
            test.setName("ProgramTest");
            for (Map<String, String> testcase : testcases) {
                XmlClass tc_class = null;
                if (testcase.get("TC_TYPE").equalsIgnoreCase("Web")) {
                    tc_class = new XmlClass(prop.getProperty("UI_TESTS_CLASSPATH") + "." + testcase.get("TC_CLASS"));
                    classNames.add(prop.getProperty("UI_TESTS_CLASSPATH") + "." + testcase.get("TC_CLASS"));
                } else if (testcase.get("TC_TYPE").equalsIgnoreCase("API")) {
                    tc_class = new XmlClass(prop.getProperty("API_TESTS_CLASSPATH") + "." + testcase.get("TC_CLASS"));
                    classNames.add(prop.getProperty("API_TESTS_CLASSPATH") + "." + testcase.get("TC_CLASS"));
                }
                xmlClasses.add(tc_class);
                if (tc_class == null)
                    continue;
                List<XmlInclude> methods = new ArrayList<>();
                methods.add(new XmlInclude(testcase.get("TC_METHOD")));
                tc_methods.add(testcase.get("TC_METHOD"));
                tc_class.setIncludedMethods(methods);
            }
            for(String names:classNames)
                System.out.println(names);
            test.setXmlClasses(xmlClasses);
            List<XmlSuite> suites = new ArrayList<>();
            suites.add(suite);
            TestNG tng = new TestNG();
            tng.setXmlSuites(suites);
            tng.run();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
