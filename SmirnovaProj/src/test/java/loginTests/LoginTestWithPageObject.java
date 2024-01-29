package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static libs.TestData.VALID_LOGIN_UI;
import static libs.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().getHeader().checkIsUserNameDisplayed();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().checkIsAvatarDisplayed();
        pageProvider.loginPage().checkIsInputPasswordNotPresent()
                .checkIsInputUsernameNotPresent();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaautoinvalid");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkIsButtonSignInPresent();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        Assert.assertTrue("Invalid login message is not visible",
                pageProvider.loginPage().isInvalidLoginMessageDisplayed());
    }
}
