package RegistrationTest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.ErrorMessage;
import org.junit.Test;
import BaseTest.BaseTest;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessageTest extends BaseTest{
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
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

        };
    }
}
