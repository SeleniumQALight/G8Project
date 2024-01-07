package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.mainPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.mainPage().clickOnButtonSingIn();
        pageProvider.headerElement().checkSignOutButtonIsVisible();
        pageProvider.headerElement().checkCreatePostButtonIsVisible();
        pageProvider.headerElement().checkUserNameIsVisible();
        pageProvider.headerElement().checkMyProfileIconIsVisible();
        pageProvider.mainPage().checkLoginFieldNotVisible();
        pageProvider.mainPage().checkPasswordFieldNotVisible();
        pageProvider.mainPage().checkSignInButtonIsNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().enterTextIntoInputLogin(WRONG_LOGIN);
        pageProvider.mainPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.mainPage().clickOnButtonSingIn();
        pageProvider.mainPage().checkSignInButtonIsVisible();
        pageProvider.mainPage().checkSignInButtonIsVisible();
        Assert.assertTrue("Warning button Invalid username/password is visible", pageProvider.mainPage().isWarningMessageVisible());

    }

}
