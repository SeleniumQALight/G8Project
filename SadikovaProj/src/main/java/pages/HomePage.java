package pages;

import libs.TestData;
import org.junit.Assert;
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
        getheaderElement().checkSignOutButtonIsVisible();
        return this;
    }

    public HeaderElement getheaderElement() {
        return new HeaderElement(webDriver);
    }

    public MainPage getMainPage(){
        return new MainPage(webDriver);
    }


    public HomePage openHomePageAndLoginIfNeeded() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openMainPage();
        if (this.getheaderElement().isButtonSignOutVisible()) {
            logger.info("User is already logged in");
        } else {
            mainPage.loginToProfile(TestData.VALID_LOGIN_UI, TestData.VALID_PASSWORD);
            checkIsRedirectToHomePage(Urls.HOME_PAGE_URL);
            logger.info("User is logged in");
        }
        return this;
    }
}