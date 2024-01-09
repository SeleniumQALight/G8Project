package loginTests;

import BaseTest.BaseTest;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {

    @Test
    @Description("Check that user can login with valid login")
    public void validLoginTest() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreate();

        Assert.assertTrue("Button 'Sign Out' is not visible", pageProvider.getHomePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Create Post' is not visible", pageProvider.getHomePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button 'My Profile' is not visible", pageProvider.getHomePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("User name is not visible", pageProvider.getHomePage().getHeader().isUserNameVisible());
        Assert.assertEquals("User name is not expected", VALID_LOGIN_UI, pageProvider.getHomePage().getHeader().getUserName());
        Assert.assertFalse("Input 'Username' is visible", pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertFalse("Input 'Password' is visible", pageProvider.getLoginPage().isInputPasswordVisible());
    }

    @Test
    @Description("Check that user cannot login with invalid login")
    public void invalidLoginTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaautoInvalid");
        pageProvider.getLoginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button 'Sign Out' is visible", pageProvider.getHomePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is not visible", pageProvider.getLoginPage().isButtonSignInVisible());
        Assert.assertTrue("Alert is not visible", pageProvider.getLoginPage().isAlertInvalidUsernamePasswordVisible());
    }

    @Test
    @Description("Check that user can logout")
    public void logoutTest() {
        pageProvider.getLoginPage().openLoginPageAndFillLoginFormWithValidCreate();

        Assert.assertTrue("Button 'Search' is not visible", pageProvider.getHomePage().getHeader().isButtonSearchVisible("Search"));
        Assert.assertTrue("Button 'Chat' is not visible", pageProvider.getHomePage().getHeader().isButtonChatVisible("Chat"));
        Assert.assertTrue("Button 'My profile' is not visible", pageProvider.getHomePage().getHeader().isButtonMyProfileVisible());
        Assert.assertTrue("Button 'Create post' is not visible", pageProvider.getHomePage().getHeader().isButtonCreatePostVisible());
        Assert.assertTrue("Button 'Sign out' is not visible", pageProvider.getHomePage().getHeader().isButtonSignOutVisible());

        pageProvider.getLoginPage()
                .checkIsInputPasswordNotVisible()
                .checkIsInputUsernameNotVisible()
                .checkIsButtonSignInNotVisible();

        pageProvider.getHomePage().getHeader().clickOnButtonSignOut().checkIsRedirectToLoginPage();

        pageProvider.getHomePage().getHeader().checkIsButtonSearchNotVisible();
        pageProvider.getHomePage().getHeader().checkIsButtonChatNotVisible();
        pageProvider.getHomePage().getHeader().checkIsButtonMyProfileNotVisible();
        pageProvider.getHomePage().getHeader().checkIsButtonCreatePostNotVisible();
        pageProvider.getHomePage().getHeader().checkIsButtonSignOutNotVisible();

        Assert.assertTrue("Input 'Username' is not visible", pageProvider.getLoginPage().isInputLoginVisible());
        Assert.assertTrue("Input 'Password' is not visible", pageProvider.getLoginPage().isInputPasswordVisible());
    }

    @Test
    @Description("Check validation messages if insert invalid data")
    public void validationMessagesRegisterFormInvalidDataTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputUsernameRegister("QA");
        pageProvider.getLoginPage().enterTextIntoInputEmailRegister("email");
        pageProvider.getLoginPage().enterTextIntoInputPasswordRegister("t1");
        pageProvider.getLoginPage().clickOnButtonSignUp();
        pageProvider.getLoginPage().checkIsValidationMessageUsernameRegisterVisible();
        pageProvider.getLoginPage().checkIsValidationMessageEmailRegisterVisible();
        pageProvider.getLoginPage().checkIsValidationMessagePasswordRegisterVisible();
    }
}
