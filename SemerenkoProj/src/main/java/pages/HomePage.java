package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    private HeaderElement headerElement;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeader() {
        return new HeaderElement(webDriver);
    }

    public HomePage checkIsButtonCreateNewPostVisible() {
        getHeader().checkIsElementButtonCreateNewPostVisible();
        return this;
    }

    public HomePage checkIsButtonSignOutVisible() {
        getHeader().checkIsElementButtonSignOutVisible();
        return this;
    }

    public HomePage checkIsLinkMyProfileVisible() {
        getHeader().checkIsElementLinkMyProfileVisible();
        return this;
    }

    public HomePage checkIsSpanUserNameVisible() {
        getHeader().checkIsElementSpanUserNameVisible();
        return this;
    }

    public HomePage checkIsInputUsernameUnvisible() {
        getHeader().checkIsElementInputUsernameUnvisible();
        return this;
    }

    public HomePage checkIsInputPasswordUnvisible() {
        getHeader().checkIsElementInputPasswordUnvisible();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        getHeader().checkIsElementButtonSignOutVisible();
        return this;
    }
}
