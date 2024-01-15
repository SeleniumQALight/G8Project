package unloginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class UnloginTestInTwoTabs extends BaseTest {
    @Test
    public void unloginTestInTwoTabs() {
        pageProvider.loginPage().openLoginPage(); // 1. Open login page
        pageProvider.loginPage().openLoginPageAndFillLoginFormWhithValidCred(); // 2 Login with valid credentials
        pageProvider.homePage().checkIsRedirectToHomePage(); // 3. Check that button SignOut is visible
        pageProvider.loginPage().openNewTabInBrowser(); // 4. Open new tab in browser using JavaScript
        pageProvider.loginPage().switchToTab(1); // 5. Switch to new tab
        pageProvider.loginPage().openLoginPage(); // 6. Open login page
        pageProvider.homePage().checkIsRedirectToHomePage(); // 7. Check that button SignOut is visible
        pageProvider.loginPage().switchToTab(0); // 8. Switch to main tab
        pageProvider.homePage().getHeader().clickOnButtonSignOut(); // 9. Click on button SignOut
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble()); // 10. Check that button SignOut is not visible
        pageProvider.loginPage().switchToTab(1); // 11. Switch to new tab
        pageProvider.loginPage().refreshPage(); // 12. Refresh page
        Assert.assertFalse("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble()); // 12. Check that button SignOut is not visible
    }
}