package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.DEFAULT_VALID_LOGIN_UI;
import static libs.TestData.DEFAULT_VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin(DEFAULT_VALID_LOGIN_UI);
        pageProvider.getLoginPage().enterTextIntoInputPassword(DEFAULT_VALID_PASSWORD_UI);
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonMyProfileIconVisible();
        pageProvider.homePage().getHeader().checkIsProfileNameVisible();
        pageProvider.homePage().getHeader().checkIsCreatePostButtonVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible();
        pageProvider.getLoginPage().checkIsPasswordInputNotVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();

//        Assert.assertFalse("Input for password is visible",
//                pageProvider.getLoginPage().isInputPasswordVisible());
//
//        Assert.assertFalse("Input for login is visible",
//                pageProvider.getLoginPage().isInputLoginVisible());
//
//        Assert.assertTrue("Button SignOut is not visible",
//                pageProvider.homePage().getHeader().isButtonSignOutVisible());

    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaavto");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().IsButtonSignOutVisible());
        Assert.assertTrue("Button SignIn is visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is visible", pageProvider.getLoginPage().isInvalidUserNamePasswordAlertVisible());

    }

    @Test
    public void signOut() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader().checkIsButtonSignOutVisible()
                .checkIsProfileNameVisible()
                .checkIsButtonMyProfileIconVisible()
                .checkIsCreatePostButtonVisible()
                .checkIsButtonChatVisible()
                .checkIsButtonSearchVisible();
        pageProvider.getLoginPage().checkIsLoginInputNotVisible()
                .checkIsPasswordInputNotVisible()
                .checkIsButtonSignInNotVisible();
        pageProvider.homePage().getHeader().clickOnButtonSignOut()
                .checkIsInputLoginVisible()
                .checkIsInputPasswordVisible()
                .checkIsButtonSignInVisible();
        pageProvider.homePage().getHeader().checkIsButtonSignOutNotVisible()
                .checkIsProfileNameNotVisible()
                .checkIsButtonMyProfileIconNotVisible()
                .checkIsCreatePostButtonNotVisible()
                .checkIsButtonChatNotVisible()
                .checkIsButtonSearchNotVisible();
    }
}
