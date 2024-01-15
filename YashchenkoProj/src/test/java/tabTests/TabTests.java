package tabTests;

import baseTest.BaseTest;
import org.junit.Test;

import static libs.TestData.DEFAULT_VALID_LOGIN_UI;
import static libs.TestData.DEFAULT_VALID_PASSWORD_UI;

public class TabTests extends BaseTest {

    @Test
    public void checkLoggedUserInTheNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader().checkIsButtonSignOutVisible();
        pageProvider.openNewTab();
        pageProvider.switchToNextTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.switchToOriginalTab();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.switchToNextTab();
        pageProvider.closeTab();
        pageProvider.switchToOriginalTab();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void checkMissingOfNotSubmittedCredentials() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
        pageProvider.refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
    }
}
