package pages;

import org.junit.Assert;;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement SignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Home page"
                , getHeader().isButtonSignOutVisble());
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public void clickOnButtonSignOut() {
        clickOnElement(SignOut);
    }
}
