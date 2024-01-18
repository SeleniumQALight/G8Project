package registrationTests;

import baseTest.BaseTest;
import junitparams.Parameters;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import org.junit.runner.RunWith;
@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {
    final String ERROR_USERNAME = "User must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")

    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
               // (ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);

    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD}
        };
    }
}
