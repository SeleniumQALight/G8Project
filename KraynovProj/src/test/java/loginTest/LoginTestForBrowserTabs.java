package loginTest;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Test;


public class LoginTestForBrowserTabs  extends BaseTest {

    @Test
    public void validLoginCheckNewTab() {
        pageProvider.loginPage().openLoginPageFillLoginFormWithValidCred().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().openNewTabInBrowser();
        pageProvider.loginPage().switchToTabInBrowser(1);
        pageProvider.loginPage().openLoginPage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().switchToTabInBrowser(0);
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().closeTabAndSwitchToMainPage();
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();
    }

    @Test
    public void fillInputAndRefreshPage() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);
        pageProvider.loginPage().refreshPage();
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();
    }

}
