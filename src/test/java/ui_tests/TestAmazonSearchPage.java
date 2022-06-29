package ui_tests;

import Pages.AmazonSearchPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestAmazonSearchPage extends BaseClass {

    @Test
    public void SearchOperation() throws IOException {
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.searchProduct("laptop");
    }

    @Test
    public void ClickAddToCart() throws InterruptedException, IOException {
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.searchProduct("laptop");
        page.AddtoCart();

    }

    @Test
    public void proceedToBuy() throws InterruptedException, IOException {
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.searchProduct("laptop");
        page.AddtoCart();
        page.proceedToBuy();
    }

}

