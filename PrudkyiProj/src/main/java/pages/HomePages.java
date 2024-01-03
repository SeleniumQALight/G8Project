package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePages extends ParentPage {
    private HeaderElement headerElement;

    public HomePages(WebDriver webDriver) {
        super(webDriver);
    }


    public HomePages checkIsRedirectToHomePage() {
        //TODO check url
        Assert.assertTrue("Invalid page - not Home Page", getHeader().isButtonSingOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}