package logoutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LogoutTest extends BaseTest {
    @Test
    public void validLogout(){
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checkIsButtonChatVisible()
                .checkIsLinkMyProfileVisible()
                .checkIsButtonCreateNewPostVisible()
                .checkIsButtonSignOutVisible()
                .checkIsInputUsernameUnvisible()
                .checkIsInputPasswordUnvisible()
                .checkIsButtonSignInUnvisible()
                .getHeader().clickOnElementButtonSignOut()
                .checkIsRedirectOnLoginPage()
                .checkIsButtonSearchUnvisible()
                .checkIsButtonChatUnvisible()
                .checkIsButtonProfileUnvisible()
                .checkIsButtonCreatePostUnvisible()
                .checkIsButtonSignOutUnvisible()
                .checkIsInputUsernameVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible();
    }
}
