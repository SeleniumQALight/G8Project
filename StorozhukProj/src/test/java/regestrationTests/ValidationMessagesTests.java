package regestrationTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)

public class ValidationMessagesTests extends BaseTest {
    final String ERROR_MESSAGE_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_MESSAGE_EMAIL = "You must provide a valid email address.";
    final String ERROR_MESSAGE_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void TC_001_checkValidationMessages(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessage);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_MESSAGE_EMAIL + SEMICOLON + ERROR_MESSAGE_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_MESSAGE_PASSWORD}
        };

    }
}
