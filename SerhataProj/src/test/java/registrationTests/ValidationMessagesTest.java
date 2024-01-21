package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class ValidationMessagesTest extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String ERROR_USERNAME_KIRILLIC = "Username can only contain letters and numbers.";
    final String SEMICOLON = ";";
    final String TEST = "тест";
    final String TEST_NAME_MAX_CHARACTERS = "Username cannot exceed 30 characters.";
    final String TEST_PASSWORD_MAX_CHARACTERS = "Password cannot exceed 50 characters.";
    final String MAX_CHARACTERS = "test111111111111111111111122222222222222222222222222222222222222222222222222222222222222222222222";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputUserName(userName);
        pageProvider.loginPage().enterTextInToInputEmail(email);
        pageProvider.loginPage().enterTextInToInputPasswordForSignUp(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD},
                {TEST, TEST, TEST, ERROR_USERNAME_KIRILLIC + SEMICOLON + ERROR_PASSWORD + SEMICOLON + ERROR_EMAIL},
                {MAX_CHARACTERS, "tr@tr.com", MAX_CHARACTERS, TEST_NAME_MAX_CHARACTERS + SEMICOLON + TEST_PASSWORD_MAX_CHARACTERS}
        };
    }

}