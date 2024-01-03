package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.loginPage().clickOnButtonSingIn();
        pageProvider.headerElement().signOutButtonIsVisible();
        pageProvider.headerElement().createPostButtonIsVisible();
        pageProvider.headerElement().userNameIsVisible();
        pageProvider.headerElement().myProfileIconIsVisible();
        pageProvider.loginPage().loginFieldNotVisible();
        pageProvider.loginPage().passwordFieldNotVisible();
        pageProvider.loginPage().signInButtonIsNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(WRONG_LOGIN);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.loginPage().clickOnButtonSingIn();
        pageProvider.headerElement().signOutButtonIsVisible();
        pageProvider.loginPage().signInButtonIsVisible();
        Assert.assertTrue("Warning button Invalid username/password is visible", pageProvider.loginPage().isWarningMessageVisible());
    }

}
