package pages;

import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check url
        getHeader().checkIsHeaderForUserVisible();
        getLoginPage().checkIsLoginFieldIsNotVisible();
        return this;
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public LoginPage getLoginPage() {
        return new LoginPage(webDriver);
    }
}
