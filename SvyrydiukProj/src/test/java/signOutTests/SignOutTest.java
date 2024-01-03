package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void logOutTest() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred();
        pageProvider.homePage().getHeader().isProfileButtonVisible();
        pageProvider.homePage().getHeader().isButtonSignOutVisible();
        pageProvider.homePage().getHeader().isButtonCreatePostVisible();
        pageProvider.homePage().getHeader().isSearchButtonVisible();
        pageProvider.homePage().getHeader().isChatButtonVisible();
        pageProvider.loginPage().checkUsernameFieldNotVisible();
        pageProvider.loginPage().checkPasswordFieldNotVisible();
        pageProvider.loginPage().checkSignInButtonNotVisible();
        pageProvider.homePage().getHeader().clickOnButtonSignOut();
        pageProvider.loginPage().checkUsernameFieltVisible();
        pageProvider.loginPage().checkPasswordFieldVisible();
        pageProvider.loginPage().isButtonSignInVisible();
        pageProvider.homePage().getHeader().isProfileButtonNotVisible();
        pageProvider.homePage().getHeader().isButtonSignOutNotVisible();
        pageProvider.homePage().getHeader().isButtonCreatePostNotVisible();
        pageProvider.homePage().getHeader().isSearchButtonNotVisible();
        pageProvider.homePage().getHeader().isChatButtonNotVisible();
    }
}
