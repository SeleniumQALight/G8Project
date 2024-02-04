package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestInNewTab  extends BaseTest {

    @Test
    public void loginTestInNewTab() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().openNewTabAndSwitchToIt();
        pageProvider.loginPage().openLoginPage();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().switchToFirstTab();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().closeNewTabAndSwitchToFirstTab();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
    }
}
