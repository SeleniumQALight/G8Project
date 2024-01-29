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

    private String ERROR_MESSAGE_USERNAME_CYRILLIC = "Username can only contain letters and numbers.";
    final String ERROR_MESSAGE_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_MESSAGE_EMAIL = "You must provide a valid email address.";
    final String ERROR_MESSAGE_PASSWORD = "Password must be at least 12 characters.";
    final String ERROR_MESSAGE_USERNAME_EXCEED_LONG = "Username cannot exceed 30 characters.";
    final String ERROR_MESSAGE_PASSWORD_EXCEED_LONG = "Password cannot exceed 50 characters.";
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
                {"taras", "tr@tr.com", "tr", ERROR_MESSAGE_PASSWORD},
                {"tr", "tr@tr.com", "tr", ERROR_MESSAGE_USERNAME},
                {"тест", "тест", "тест", ERROR_MESSAGE_USERNAME_CYRILLIC + SEMICOLON + ERROR_MESSAGE_EMAIL + SEMICOLON + ERROR_MESSAGE_PASSWORD},
                {"USERNAMElengthValidationtest12345678901234567890123", "tr@tr.com", "PASSWORDlengthValidationtest12345678901234567890123",
                        ERROR_MESSAGE_USERNAME_EXCEED_LONG + SEMICOLON + ERROR_MESSAGE_PASSWORD_EXCEED_LONG},
        };

    }
}
