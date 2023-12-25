package loginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

       Assert.assertTrue("Button SignOut is visible", pageProvider.homePage().isButtonSignOutVisble());
    }
    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("1qaauto");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();

        Assert.assertTrue("Button SignIn is visible", pageProvider.loginPage().isButtonSignInVisble());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisble());
        Assert.assertTrue("Error message is visible", pageProvider.loginPage().isErrorMessageVisible());
    }
}
