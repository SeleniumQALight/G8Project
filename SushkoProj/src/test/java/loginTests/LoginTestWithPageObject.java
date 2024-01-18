package loginTests;

import baseTest.BaseTest;
import libs.ConfigProperties;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
        pageProvider.loginPage().enterTextIntoInputPassword(VALID_PASSWORD_UI);
        pageProvider.loginPage().clickOnButtonSingIn();

        pageProvider.homePage().getHeader().checkAllElementsInHeaderAreVisible();
        pageProvider.loginPage().checkAllElementsFromLoginFormAreInvisible();

//I did it first via assert, then replaced with new created methods, but they do more checks than specified in the task.
// it looks better through the methods, but I commented the first option just in case
//
//        Assert.assertTrue("Button SignOut is not visible",
//                pageProvider.homePage().getHeader().isButtonSignOutVisible());
//        Assert.assertTrue("Button Create Post is not visible",
//                pageProvider.homePage().getHeader().isButtonCreatePostIsVisible());
//        Assert.assertTrue("Button My Profile is not visible",
//                pageProvider.homePage().getHeader().isButtonMyProfileIsVisible());
//        Assert.assertTrue("User profile name is not visible",
//                pageProvider.homePage().getHeader().isSpanUserProfileNameIsVisible());

//        Assert.assertFalse("Field for login is visible",
//                pageProvider.loginPage().isInputLoginIsVisible());
//        Assert.assertFalse("Field for password is visible",
//                pageProvider.loginPage().isInputPasswordIsVisible());

    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSingIn();

        pageProvider.homePage().getHeader().checkAllElementsInHeaderAreVisible();
        pageProvider.loginPage().checkAllElementsFromLoginFormAreInvisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin("qaauto_1");
        pageProvider.loginPage().enterTextIntoInputPassword("123456qwerty");
        pageProvider.loginPage().clickOnButtonSingIn();

        Assert.assertTrue("Button SignIn is not visible",
                pageProvider.loginPage().isButtonSignInIsVisible());
        Assert.assertFalse("Button SignOut is visible",
                pageProvider.homePage().getHeader().isButtonSignOutVisible());
        Assert.assertTrue("Validation message is not displayed",
                pageProvider.loginPage().isValidationMessageIsDisplayed());
    }
}
