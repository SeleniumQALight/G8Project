package logoutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class LogoutTest extends BaseTest {
    @Test
    public void validLogout() {
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .checkIsButtonChatVisible()
                .checkIsLinkMyProfileVisible()
                .checkIsButtonCreateNewPostVisible()
                .checkIsButtonSignOutVisible()
                .redirectOnLoginPage()
                .checkIsInputUsernameUnvisible()
                .checkIsInputPasswordUnvisible()
                .checkIsButtonSignInUnvisible()
                .redirectToHomePage()
                .getHeader().clickOnElementButtonSignOut()
                .checkIsRedirectOnLoginPage()
                .checkIsInputUsernameVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible()
                .redirectToHeaderElement()
                .checkIsButtonSearchUnvisible()
                .checkIsButtonChatUnvisible()
                .checkIsButtonMyProfileUnvisible()
                .checkIsButtonCreateNewPostUnvisible()
                .checkIsButtonSignOutUnvisible();

    }
}
