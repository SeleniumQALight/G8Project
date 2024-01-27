package registrationTests;

import baseTast.BaseTest;
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
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
    final String PASSWORD_CANNOT_EXCEED_50 = "Password cannot exceed 50 characters.";
    final String INCORRECT_NAME_FORMAT = "Username can only contain letters and numbers.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void registrationValidation(String username, String email, String password, String expectedMassage) {
        pageProvider.loginPage()
                .openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUsernameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorMessages(expectedMassage);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"tanya", "tr", "123456",  ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"tanya", "tr@tr.com", "tr", ERROR_PASSWORD},
                {"tanya", "test@test.com", "T".repeat(61), PASSWORD_CANNOT_EXCEED_50},
                {"user", "invalid_email", "T".repeat(12), ERROR_EMAIL},

                {" ", "test@test.com", "T".repeat(12), ERROR_USERNAME},
                {"!@#$%^", "test@test.com", "T".repeat(12), INCORRECT_NAME_FORMAT},


        };
    }
}