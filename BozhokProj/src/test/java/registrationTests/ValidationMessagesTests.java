package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessageTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextRegistrationEmailField(email);
        pageProvider.loginPage().enterTextRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
    }

    public Object[][] parametersForValidationMessageTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD}
        };
    }
}

