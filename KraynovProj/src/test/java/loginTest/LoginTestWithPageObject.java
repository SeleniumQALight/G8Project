package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
       pageProvider.loginPage().openLoginPage();
       pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
       pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
       pageProvider.loginPage().clickOnButtonSignIn();
       Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
       pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
       pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
       pageProvider.homePage().getHeader().checkIsMyProfileButtonVisible();
       pageProvider.homePage().getHeader().checkIsUserNameVisible();
       pageProvider.homePage().getHeader().checkTextInUsername(VALID_LOGIN_UI);
       pageProvider.loginPage().checkIsInputLoginNotVisible();
       pageProvider.loginPage().checkIsInputPasswordNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("INCORRECT_LOGIN)");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Button 'Sign In' is visible", pageProvider.loginPage().isButtonSignInVisible());
        Assert.assertTrue("Error message 'Invalid username/password' is visible", pageProvider.loginPage().isErrorMessageVisible());
    }

}
