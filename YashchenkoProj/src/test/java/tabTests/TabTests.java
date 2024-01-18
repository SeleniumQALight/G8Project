package tabTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.DEFAULT_VALID_LOGIN_UI;
import static data.TestData.DEFAULT_VALID_PASSWORD_UI;

public class TabTests extends BaseTest {

    @Test
    public void checkLoggedUserInTheNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTab();
        pageProvider.getLoginPage().switchToTabByIndex(1);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().switchToTabByIndex(0);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().switchToTabByIndex(1);
        pageProvider.getLoginPage().closeTab();
        pageProvider.getLoginPage().switchToTabByIndex(0);
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void checkMissingOfNotSubmittedCredentials() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
        pageProvider.getLoginPage().refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
    }
}
