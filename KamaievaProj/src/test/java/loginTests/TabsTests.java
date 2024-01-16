package loginTests;

import baseTest1.BaseTest;
import jdk.jfr.Description;
import org.junit.Test;

public class TabsTests extends BaseTest {

    @Test
    @Description("Check that a logged-in user remains logged in when switching to a new tab")
    public void userStillBeLoginIfOpenNewTab() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreate();
        pageProvider.getHomePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTabInBrowser();
        pageProvider.getLoginPage().switchToTabInBrowser(1);
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.getHomePage().switchToTabInBrowser(0);
        pageProvider.getHomePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.getLoginPage().closeTab(1);
        pageProvider.getHomePage().switchToTabInBrowser(0);
        pageProvider.getHomePage().getHeader().checkIsButtonSignOutVisible();
    }
}