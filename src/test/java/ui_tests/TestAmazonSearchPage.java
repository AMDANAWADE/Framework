package ui_tests;

import Pages.AmazonSearchPage;
import Utilities.BaseClass;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class TestAmazonSearchPage extends BaseClass {

    @Test(dataProvider = "input_data")
    public void search_operation(Map<String, String> input_data) throws InterruptedException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.search_product(input_data.get("PRODUCT"));
    }

    @Test(dataProvider = "input_data")
    public void click_add_to_cart(Map<String, String> input_data) throws InterruptedException{
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.search_product(input_data.get("PRODUCT"));
        page.add_to_cart();

    }

    @Test(dataProvider = "input_data")
    public void proceed_to_buy(Map<String, String> input_data) throws InterruptedException {
        openBrowser(String.valueOf(input_data.get("BROWSER")));
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.search_product(input_data.get("PRODUCT"));
        page.add_to_cart();
        page.proceed_to_buy();
    }

}

