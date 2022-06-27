package runner;

import Utilities.CommonUtil;
import Utilities.ExcelHandling;
import Utilities.ListenersImplementation;
import Utilities.PropertiesFileHandler;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.asserts.SoftAssert;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.*;

public class Runner {
    public static Map<String, String> ui_tests = new HashMap<>();

    public static void main(String[] args) {
        PropertiesFileHandler prop = new PropertiesFileHandler("config.properties");
        String runnerFile = prop.getProperty("RUNNER_FILE_PATH");
        List<Map<String, String>> testcases = new LinkedList<>();
        List<java.lang.Class<? extends ITestNGListener>> listeners = new ArrayList<>();
        SoftAssert softAssert = new SoftAssert();
        if (runnerFile != null) {
            ExcelHandling excelHandlingRunner = new ExcelHandling(runnerFile, 0);
            int col_index = excelHandlingRunner.get_column_index_having_value(0, "EXECUTE");
            int row_index = excelHandlingRunner.get_row_index(col_index, "YES");
            List<Integer> row_indexes = excelHandlingRunner.get_row_indexes_having_value_in_column(row_index, col_index, "YES");
            testcases = excelHandlingRunner.get_test_cases(row_indexes);
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
            Set<String> allClassMethods;
            XmlSuite testSuite = new XmlSuite();
            testSuite.setParallel(XmlSuite.ParallelMode.TESTS);
            XmlTest parallelTests = new XmlTest(testSuite);
            parallelTests.setName("Parallel Tests");
            parallelTests.setParallel(XmlSuite.ParallelMode.METHODS);
            parallelTests.setThreadCount(Integer.parseInt(prop.getProperty("PARALLEL_EXECUTION_THREAD_COUNT")));
            XmlTest sequentialTests = new XmlTest(testSuite);
            sequentialTests.setName("Sequential Tests");
            sequentialTests.setParallel(XmlSuite.ParallelMode.NONE);
            for (Map<String, String> testcase : testcases) {
                XmlClass tc_class = null;
                if (testcase.get("TC_TYPE").equalsIgnoreCase("Web")) {
                    tc_class = new XmlClass(prop.getProperty("UI_TESTS_CLASSPATH") + "." + testcase.get("TC_CLASS"));
                    ui_tests.put(testcase.get("TC_NAME"), testcase.get("TC_METHOD"));
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
                if (testcase.get("PARALLEL").equalsIgnoreCase("Yes")) {
                    parallelTests.getClasses().add(tc_class);
                } else {
                    sequentialTests.getClasses().add(tc_class);
                }
            }
            allClassMethods = CommonUtil.getMethods(classNames);
            for (String tc_method : tc_methods) {
                boolean value = allClassMethods.contains(tc_method);
                softAssert.assertTrue(value);
            }

            List<XmlSuite> suites = new ArrayList<>();
            suites.add(testSuite);
            TestNG tng = new TestNG();
            listeners.add(ListenersImplementation.class);
            tng.setListenerClasses(listeners);
            tng.setXmlSuites(suites);
            tng.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
