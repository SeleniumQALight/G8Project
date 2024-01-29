package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class LoginTestInNewTab  extends BaseTest {

    @Test
    public void loginTestInNewTab() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectOnHomePage()
                .getHeader().isButtonSignOutVisible();
        pageProvider.loginPage().openNewTabAndSwitchToIt();
        pageProvider.loginPage().openLoginPage();
        pageProvider.homePage().getHeader().isButtonSignOutVisible();
        pageProvider.loginPage().switchToFirstTab();
        pageProvider.homePage().getHeader().isButtonSignOutVisible();
        pageProvider.loginPage().switchToFirstTab();
        pageProvider.loginPage().closeNewTabAndSwitchToFirstTab();
        pageProvider.homePage().getHeader().isButtonSignOutVisible();

    }
}
