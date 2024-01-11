package signOutTest;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {

    @Test
    public void signOutTest() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeader().checkIsButtonFindVisible()
                .CheckIsButtonChatVisible()
                .checkIsAvatarVisible()
                .checkIsButtonCreatePostVisible()
                .checkIsButtonSignOutVisible()
                .checkIsInputLoginNotVisible()
                .checkIsInputPasswordNotVisible()
                .checkIsButtonSignInNotVisible()
                .clickOnButtonSignOut()
                .checkIsRedirectToLoginPage();

        pageProvider.homePage().getHeader().checkIsButtonFindNotVisible()
                .checkIsButtonChatNotVisible()
                .checkIsAvatarNotVisible()
                .checkIsButtonCreatePostNotVisible()
                .checkIsButtonSignOutNotVisible()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible()
        ;

    }
}
