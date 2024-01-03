package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }



    public HomePage checkIsRedirectedToHomePage() {
        // TODO check url
        Assert.assertEquals("Invalid page"
                , "https://aqa-complexapp.onrender.com/"
                , webDriver.getCurrentUrl());
        Assert.assertTrue("Invalid page - not Home Page"
                , getHeader().IsButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}
