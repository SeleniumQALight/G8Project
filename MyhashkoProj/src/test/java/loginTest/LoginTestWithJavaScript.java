package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithJavaScript extends BaseTest {


    @Test
    public void login() {
        pageProvider.loginPage().openLoginPage(); // 1. Open login page
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI); // 2.1 Login with valid credentials
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI); // 2.2 Login with valid credentials
        pageProvider.loginPage().clickOnButtonSignIn(); // 2.3 Login with valid credentials

        pageProvider.homePage().checkIsRedirectToHomePage(); // 3. Check that button SignOut is visible

        pageProvider.loginPage().openNewTabInBrowser(); // 4. Open new tab in browser using JavaScript
        pageProvider.loginPage().switchToTab(1); // 5. Switch to new tab
        pageProvider.loginPage().openLoginPage(); // 6. Open login page
        pageProvider.homePage().checkIsRedirectToHomePage(); // 7. Check that button SignOut is visible
        pageProvider.loginPage().switchToTab(0); // 8. Switch to main tab
        pageProvider.homePage().checkIsRedirectToHomePage(); // 9. Check that button SignOut is visible
        pageProvider.loginPage().closeTab(1); //10. Close new tab and switch to main tab
        pageProvider.loginPage().switchToTab(0);
        pageProvider.homePage().checkIsRedirectToHomePage(); // 11. Check that button SignOut is visible
    }
}



