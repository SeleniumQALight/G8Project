package loginTest;

import BaseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qaauto");
        pageProvider.loginPage().enterTextInToInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button sign out is not visible",
                pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().isButtonSignOutVisible());
        Assert.assertTrue("Warning message is visible", pageProvider.loginPage().isWarningMessageVisible());
        Assert.assertTrue("Button Sign In is visible", pageProvider.loginPage().isButtonSignInVisible());
    }
}
