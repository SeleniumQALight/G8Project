package signOutTest;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().checkIsButtonFindVisible()
                .checkIsButtonChatVisible()
                .checkIsAvatarVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSignOutVisible();

        pageProvider.loginPage().checkIsInputLoginNotVisible()
                .checkIsInputPasswordNotVisible()
                .checkIsButtonSignInNotVisible();

        pageProvider.homePage().getHeader().clickOnButtonSignOut()
                .checkIsRedirectToLoginPage();

        pageProvider.homePage().getHeader().checkIsButtonFindNotVisible()
                .checkIsButtonChatNotVisible()
                .checkIsAvatarNotVisible()
                .checkIsButtonCreatePostNotVisible()
                .checkIsButtonSignOutNotVisible();

        pageProvider.loginPage().checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible()
        ;

    }
}