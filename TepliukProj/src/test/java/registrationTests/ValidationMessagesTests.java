package registrationTests;

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
    final String ERROR_USERNAME_LANGUAGE = "Username can only contain letters and numbers.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
    final String ERROR_LONG_USERNAME = "Username cannot exceed 30 characters.";
    final String ERROR_LONG_PASSWORD = "Password cannot exceed 50 characters.";

    @Test
        @Parameters(method = "ParametersForValidationMessagesTests")

    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessage(expectedMessages);
    }

    public Object[][] ParametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD},
                {"VeryLongLogin_VeryLongLogin_VeryLongLogin", "tr@tr.com", "VeryLongPassword_VeryLongPassword_VeryLongPassword_VeryLongPassword", ERROR_LONG_USERNAME + SEMICOLON + ERROR_LONG_PASSWORD},
                {"юзер", "емейл", "пароль", ERROR_USERNAME_LANGUAGE + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}

        };
    }
}