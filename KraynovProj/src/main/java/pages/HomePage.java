package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Home Page", getHeader().isButtonSignOutVisible());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }
}
