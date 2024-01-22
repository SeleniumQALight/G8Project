package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
public class ValidationLogin extends BaseTest {

    final String ERROR_MESSAGE = "Invalid username/password.";

    @Test
    @Parameters(method = "parametersForValidationMessages")
    public void validationMessageTest(String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage()
                .enterTextIntoInputLogin(email)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSignIn();
        pageProvider.loginPage().checkErrorMessageInLoginForm(expectedMessages);

    }

    public Object[][] parametersForValidationMessages() {
        return new Object[][]{
                {VALID_LOGIN_UI, "tr", ERROR_MESSAGE},
                {"myName", VALID_PASSWORD_UI, ERROR_MESSAGE},
                {VALID_LOGIN_UI, "", ERROR_MESSAGE},
                {"", VALID_PASSWORD_UI, ERROR_MESSAGE},
                {"", "", ERROR_MESSAGE},
        };
    }
}