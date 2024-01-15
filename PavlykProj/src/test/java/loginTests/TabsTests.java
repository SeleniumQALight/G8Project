package loginTests;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class TabsTests extends BaseTest {

    @Test
    public void validLoginCheckNewTab() {
        pageProvider.loginPage()                                                            // Open login page
                .openLoginPageAndFillLoginFormWithValidCred()                               // Login with valid credentials
                .getHeader().checkIsButtonSignOutVisible();                                 // Check that button SignOut is visible
        pageProvider.loginPage().openNewTabInBrowser();                                     // Open new tab in browser using JavaScript
        pageProvider.loginPage().switchToTabInBrowser(1);                                   // Switch to new tab
        pageProvider.loginPage().openLoginPage()                                            // Open login page
                .getHeader().checkIsButtonSignOutVisible();                                 // Check that button SignOut is visible
        pageProvider.loginPage().switchToTabInBrowser(0);                                   // Switch to main tab
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();                 // Check that button SignOut is visible
        pageProvider.loginPage().closeTabAndSwitchToMainPage();                             // Close new tab and switch to main tab
        pageProvider.loginPage().getHeader().checkIsButtonSignOutVisible();                 // Check that button SignOut is visible
    }

    @Test
    public void fillInputAndRefreshPage() {
        pageProvider.loginPage().openLoginPage();                                           // Open login page
        pageProvider.loginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);          // Enter 'qaauto' login into input Login
        pageProvider.loginPage().enterTextIntoInputPassword(TestData.VALID_PASSWORD_UI);    // Enter '123456qwerty' password into input Password
        pageProvider.loginPage().refreshPage();                                             // Refresh page
        pageProvider.loginPage().clickOnButtonSignIn();                                     // Click on button SingIn
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible();               // Check that button SignOut is not visible
    }

    @Test
    public void validLogOutCheckNewTab() {
        pageProvider.loginPage()                                                            // Open login page
                .openLoginPageAndFillLoginFormWithValidCred()                               // Login with valid credentials
                .getHeader().checkIsButtonSignOutVisible();                                 // Check that button SignOut is visible
        pageProvider.loginPage().openNewTabInBrowser();                                     // Open new tab in browser using JavaScript
        pageProvider.loginPage().switchToTabInBrowser(1);                                   // Switch to new tab
        pageProvider.loginPage().openLoginPage()                                            // Open login page
                .getHeader().checkIsButtonSignOutVisible();                                 // Check that button SignOut is visible
        pageProvider.loginPage().switchToTabInBrowser(0);                                   // Switch to main tab
        pageProvider.loginPage().getHeader().clickOnButtonSignOut();                        // Click on button SignOut
        pageProvider.loginPage().getHeader().checkIsButtonSignOutNotVisible();              // Check that button SignOut is not visible
        pageProvider.loginPage().switchToTabInBrowser(1);                                   // Switch to new tab
        pageProvider.loginPage().refreshPage();                                             // Refresh page
        pageProvider.loginPage().getHeader().checkIsButtonSignOutNotVisible();              // Check that button SignOut is not visible
    }
}
