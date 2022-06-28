package ui_tests;

import Pages.AmazonSearchPage;
import Utilities.DriverFactory;
import org.testng.annotations.Test;

public class TestAmazonSearchPage extends BaseClass {

    @Test(priority = 1)
    public void SearchOperation() throws InterruptedException {
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.searchProduct("laptop");
    }

    @Test(priority = 2)
    public void ClickAddToCart() throws InterruptedException {
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.searchProduct("laptop");
        page.AddtoCart();

    }

    @Test(priority = 3)
    public void proceedToBuy() throws InterruptedException {
        AmazonSearchPage page = new AmazonSearchPage(DriverFactory.getDriver());
        page.searchProduct("laptop");
        page.AddtoCart();
        page.proceedToBuy();
    }

}

