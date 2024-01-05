package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void signOut() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().isHeaderOfTheLoggedInUserDisplayed();
        pageProvider.loginPage().isInputPasswordNotPresent()
                .isInputUsernameNotPresent()
                .isButtonSignInPresent();
        pageProvider.homePage().getHeader().clickOnButtonSignOut()
                .checkIsRedirectToLoginPage()
                .isInputPasswordPresent()
                .isInputUsernamePresent()
                .isButtonSignInPresent();
        pageProvider.homePage().getHeader().isHeaderOfTheLoggedInUserNotDisplayed();
    }
}
