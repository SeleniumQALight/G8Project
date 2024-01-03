package pages;

import libs.Urls;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openHomePage() {
        goToWebPage(Urls.HOME_PAGE_URL);
    }

    public HomePage checkIsRedirectToHomePage(String url) {
        assertUrl(url);
        headerElement().signOutButtonIsVisible();
        return this;
    }

    public HeaderElement headerElement() {
        return new HeaderElement(webDriver);
    }
}