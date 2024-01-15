package tabTests;

import baseTest.BaseTest;
import org.junit.Test;

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
}
