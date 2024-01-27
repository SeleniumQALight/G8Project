package RegistrationTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ErrorMessage;
import org.junit.Test;
import BaseTest.BaseTest;
import org.junit.runner.RunWith;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTest extends BaseTest{
   final String SEMICOLON = ";";
    final String ERROR_USERNAME_FORMAT_CHECK = "Username can only contain letters and numbers.";
    final String ERROR_USERNAME_LENGTH_EXCEEDED = "Username can't be more 30 characters.";
    final String ERROR_PASSWORD_LENGTH_EXCEEDED = "Password cannot exceed 50 characters.";

    final String USER_NAME_MESSAGE = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().inputTextIntoRegistrationLogin(userName);
        pageProvider.loginPage().inputTextIntoRegistrationEmail(email);
        pageProvider.loginPage().inputTextIntoRegistrationPassword(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);

    }
    public Object[][] parameterForValidationMessagesTests() {
        return new Object[][]{
                {"nataliia", "tr", "tr", ErrorMessage.EMAIL_MESSAGE + SEMICOLON + ErrorMessage.PASSWORD_MESSAGE},
                {"nataliia", "tr@tr.com", "tr", ErrorMessage.PASSWORD_MESSAGE},
                {"Testnameforlengthvalidationtest", "tssr@gmail.com", "Passwordlengthvalidationtest12345678901234567890123", ERROR_USERNAME_LENGTH_EXCEEDED + SEMICOLON + ERROR_PASSWORD_LENGTH_EXCEEDED},
                {"test", "test", "test", ERROR_USERNAME_FORMAT_CHECK + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };
    }
    @Test
    @Parameters(method = "parametersForInValidLoginWithParamsTest")
    public void inValidLoginWithParams(String userName, String password) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(userName);
        pageProvider.loginPage().enterTextInToInputPassword(password);
        pageProvider.loginPage().clickOnButtonSingIn();
        pageProvider.loginPage().checkIsInvalidUsernameOrPasswordMessageVisible();
    }

    public Object[][] parametersForInValidLoginWithParamsTest() {
        return new Object[][]{
                {VALID_LOGIN_UI, "invalid_password"},
                {"invalid_username", VALID_PASSWORD_UI},
                {"!@#(*#&*#&^&#&^%#&", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuio"},
                {"test", "test"}
        };
    }
}
