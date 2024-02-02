package loginTests;

import baseTest.BaseTest;
import io.qameta.allure.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import categories.SmokeTestFilter;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;


@RunWith(JUnitParamsRunner.class)

@Epic("Allure examples")
@Feature("Junit 4 support")

public class LoginTestWithPageObject extends BaseTest {

    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")

    @Test
    @Category(SmokeTestFilter.class)
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

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextInToInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Button SignOut is not visible", pageProvider.homePage().getHeader().isButtonSignOutPresent());
        Assert.assertTrue("Button Create Post is not visible", pageProvider.homePage().getHeader().isButtonCreatePostPresent());
        Assert.assertTrue("Title My Profile is not visible", pageProvider.homePage().getHeader().isTitleMyProfilePresent());
        Assert.assertTrue("User Name is not visible", pageProvider.homePage().getHeader().isUserNamePresent());
        pageProvider.loginPage().checkIsLoginFieldIsNotVisible();
    }

    @Parameters(method = "parametersForLoginValidationMessagesTests")
    @Test
    public void invalidLoginMessagesTests(String login, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(login);
        pageProvider.loginPage().enterTextInToInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkIsLoginErrorVisible();
    }

    public Object[][] parametersForLoginValidationMessagesTests() {
        return new Object[][]{
                {"tr", "tr"},
                {"@#$$#@", "@#$$#@"},
                {"1","1"},
                {"  ", "  "}
        };
    }
}
