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
public class ValidationMessegesTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String CYRILLIC_USERNAME = "Username can only contain letters and numbers.";
    final String TO_LONG_PASSWORD = "Password cannot exceed 50 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessegesTests")
    public void ValidationMessegesTests(String username, String email, String password, String expectedMesseges) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUsernameField(username);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessage(expectedMesseges);
    }

    public Object[][] parametersForValidationMessegesTests() {
        return new Object[][]{
                {"Tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"Taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"тест", "email@test.gmail.com", "tr", CYRILLIC_USERNAME + SEMICOLON + ERROR_PASSWORD},
                {"Taras", "email@test.gmail.com", "trasdasda".repeat(6), TO_LONG_PASSWORD}

        };
    }
}
