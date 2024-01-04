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

       Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());
       Assert.assertTrue("Button Create Post is visible", pageProvider.homePage().getHeader().isButtonCreatePostVisble());
       Assert.assertTrue("Button MyProfile is visible", pageProvider.homePage().getHeader().isButtonMyProfileVisble());
       Assert.assertTrue("Login is visible", pageProvider.homePage().getHeader().isLoginVisible());
       Assert.assertFalse("Field for input login is not visible", pageProvider.loginPage().isInputLoginVisible());
       Assert.assertFalse("Field for input password is not visible", pageProvider.loginPage().isInputPasswordVisible());
    }
    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("1qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisble());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisble());
        Assert.assertTrue("Error message is visible", pageProvider.loginPage().isErrorMessageVisible());
    }
}
