package loginTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ConfigProvider;
import libs.ExcelDriver;
import libs.Util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Map;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
public class LoginTestWithPageObjectWithParameters extends BaseTest {


    @Test
    @Parameters(method = "parametersForValidationMessagesLoginFieldsTests")
        public void validationMessagesForLoginTests(String login, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(login);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        Assert.assertTrue("Warning message is visible", pageProvider.loginPage().isWarningMessageVisible());
    }

    public Object[][] parametersForValidationMessagesLoginFieldsTests() {
        return new Object[][]{
                {"taras", "tr"},
                {"", "com123"},
                {"taras", ""},
                {"", ""}
        };
    }
}
