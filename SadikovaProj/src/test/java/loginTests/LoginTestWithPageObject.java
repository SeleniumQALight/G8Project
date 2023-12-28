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
        Assert.assertTrue("Button SignOut is not visible",
                pageProvider.homePage().isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin(){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(WRONG_LOGIN);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD);
        pageProvider.loginPage().clickOnButtonSingIn();
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Warning button Invalid username/password is visible", pageProvider.loginPage().isWarningMessageVisible());
    }

}
