package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import jdk.jfr.Description;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestFilter.class)
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

    @Test
    @Description("Check that user can login with valid login")
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");

        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().checkIsMyProfileButtonVisible();
        pageProvider.homePage().getHeader().checkIsUserNameVisible();
        pageProvider.homePage().getHeader().checkTextInUsername(dataForValidLogin.get("login"));
        pageProvider.loginPage().checkIsInputLoginNotVisible();
        pageProvider.loginPage().checkIsInputPasswordNotVisible();

        Assert.assertTrue("Button 'SignOut' is not visible", pageProvider.homePage().getHeader().isButtonSignOutVisible());
    }

}
