package pages;

import data.TestData;
import libs.Urls;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openHomePage() {
        goToWebPage(baseUrl);
    }

    public HomePage checkIsRedirectToHomePage() {
        assertUrl();
        getheaderElement().checkSignOutButtonIsVisible();
        return this;
    }

    public HeaderElement getheaderElement() {
        return new HeaderElement(webDriver);
    }

    public MainPage getMainPage() {
        return new MainPage(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public HomePage openHomePageAndLoginIfNeeded() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        if (this.getheaderElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            mainPage.loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
            checkIsRedirectToHomePage();
            logger.info("User is logged in");
        }
        return this;
    }



}