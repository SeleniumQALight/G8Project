package SignUpTests;

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
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_USERNAME_FORMAT = "Username can only contain letters and numbers.";
    final String ERROR_USERNAME_LENGTH_EXCEEDED = "Username cannot exceed 30 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String ERROR_PASSWORD_LENGTH_EXCEEDED = "Password cannot exceed 50 characters.";
    final String SEMICOLON = ";";
    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tssr@te.com", "tr", ERROR_PASSWORD},
                {"Testnameforlengthvalidationtest", "tssr@te.com", "Passwordlengthvalidationtest12345678901234567890123", ERROR_USERNAME_LENGTH_EXCEEDED + SEMICOLON + ERROR_PASSWORD_LENGTH_EXCEEDED},
                {"тест", "тест", "тест", ERROR_USERNAME_FORMAT + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };
    }

    @Test
    public void validationMessagesTestUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(5);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressEnterKey();
        pageProvider.loginPage().checkErrorMessages("Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
