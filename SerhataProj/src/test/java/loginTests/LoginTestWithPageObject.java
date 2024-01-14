package loginTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostPresent());
        Assert.assertTrue("Title My Profile is not visible", pageProvider.homePage().getHeader().isTitleMyProfilePresent());
        Assert.assertTrue("User Name is not visible", pageProvider.homePage().getHeader().isUserNamePresent());
        pageProvider.loginPage().checkIsLoginFieldIsNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin("qa11111");
        pageProvider.loginPage().enterTextInToInputPassword("1230000123");
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button Sign is not visible", pageProvider.loginPage().isButtonSignInPresent());
        Assert.assertTrue("Error message is not visible", pageProvider.loginPage().isErrorMessagePresent());
        Assert.assertFalse("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
    }

    @Test
    public void validLoginUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKeyNTimes(2);
        pageProvider.loginPage().enterTextInToInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().pressTabKeyNTimes(1);
        pageProvider.loginPage().enterTextInToInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().pressEnterKey();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
    }
}
