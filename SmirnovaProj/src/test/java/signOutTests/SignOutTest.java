package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void signOut() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().checkIsHeaderOfTheLoggedInUserDisplayed();
        pageProvider.loginPage().checkIsInputPasswordNotPresent()
                .checkIsInputUsernameNotPresent()
                .checkIsButtonSignInNotPresent();
        pageProvider.homePage().getHeader().clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
                .checkIsInputPasswordPresent()
                .checkIsInputUsernamePresent()
                .checkIsButtonSignInPresent();
        pageProvider.homePage().getHeader().checkIsHeaderOfTheLoggedInUserNotDisplayed();
    }
}
