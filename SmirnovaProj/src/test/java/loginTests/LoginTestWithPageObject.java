package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)


public class LoginTestWithPageObject extends BaseTest {
    final String ERROR_MESSAGE = "Invalid username/password.";

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
    @Parameters(method = "parametersForInvalidLoginTests")

    public void invalidLogin(String login, String password, String errorMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkIsButtonSignInPresent();
        pageProvider.loginPage().checkErrorsMessagesLogin(errorMessage);
    }

    private Object[][] parametersForInvalidLoginTests() {
        return new Object[][]{
                {"qaautoinvalid", "123456qwerty", ERROR_MESSAGE},
                {"", "123456qwerty", ERROR_MESSAGE},
                {"qaautoinvalid", "", ERROR_MESSAGE},
                {"", "", ERROR_MESSAGE}

        };
    }

    @Test
    public void validLoginWithExcel() throws IOException {
        Map<String, String> dataForValidLogin =
                ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(), "validLogOn");
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(dataForValidLogin.get("login"));
        pageProvider.loginPage().enterTextIntoInputPassword(dataForValidLogin.get("pass"));
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.homePage().getHeader().checkIsUserNameDisplayed();
        pageProvider.homePage().getHeader().checkIsButtonCreatePostVisible();
        pageProvider.homePage().getHeader().checkIsAvatarDisplayed();
        pageProvider.loginPage().checkIsInputPasswordNotPresent()
                .checkIsInputUsernameNotPresent();
    }
}
