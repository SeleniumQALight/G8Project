package registratinTests;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static data.TestData.VALID_PASSWORD_UI;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessagesTests extends BaseTest {


    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
    final String PASSWORD_CANNOT_EXCEED_50 = "Password cannot exceed 50 characters.";
    final String NAME_FIELD_CAN_ONLY_CONTAIN_LETTERS_AND_NUMBERS = "Username can only contain letters and numbers.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegistration(userName);
        pageProvider.loginPage().enterTextIntoInputEmailRegistration(email);
        pageProvider.loginPage().enterTextIntoInputPasswordRegistration(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"myName", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"myName", "tr@tr.cm", "tr", ERROR_PASSWORD},
                {"myName", "rtytrrrr@gmail.com", "tr".repeat(30), PASSWORD_CANNOT_EXCEED_50},
                {"майName", "rtytrrrr@gmail.com", VALID_PASSWORD_UI, NAME_FIELD_CAN_ONLY_CONTAIN_LETTERS_AND_NUMBERS}
        };
    }
}