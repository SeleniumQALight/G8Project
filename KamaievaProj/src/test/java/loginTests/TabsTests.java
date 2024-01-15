package loginTests;

import baseTest1.BaseTest;
import jdk.jfr.Description;
import org.junit.Test;

public class TabsTests extends BaseTest {

    @Test
    @Description("Check that a logged-in user remains logged in when switching to a new tab")
    public void userStillBeLoginIfOpenNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreate();
        pageProvider.getHomePage().getHeader().isButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTabInBrowser();
        pageProvider.getLoginPage().switchToNewTabInBrowser();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeader().isButtonSignOutVisible();
        pageProvider.getHomePage().switchToMainTabInBrowser();
        pageProvider.getHomePage().getHeader().isButtonSignOutVisible();
        pageProvider.getLoginPage().closeNewTabAndSwitchToMainTab();
        pageProvider.getHomePage().getHeader().isButtonSignOutVisible();
    }
}