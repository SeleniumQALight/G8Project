package registerFormTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
    final String ERROR_USERNAME_KIRILICA = "Username can only contain letters and numbers.";
    final String ERROR_PASSWORD_60_numbers = "Password cannot exceed 50 characters.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void ValidationMessagesTests(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegister(userName);
        pageProvider.loginPage().enterTextIntoInputEmailRegister(email);
        pageProvider.loginPage().enterTextIntoInputPasswordRegister(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    private Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"tr", "tr", "tr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}, // 1 2 3
                {"tr", "tr", "trtrtrtrtrtrtr", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL}, // 1 2
                {"tr", "tr@tr.com", "tr", ERROR_USERNAME + SEMICOLON + ERROR_PASSWORD}, // 1 3
                {"tr", "tr@tr.com", "trtrtrtrtrtrtr", ERROR_USERNAME}, // 1
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}, // 2 3
                {"taras", "tr", "trtrtrtrtrtrtr", ERROR_EMAIL}, // 2
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD}, // 3
                {"тест", "tr@tr.com", "1".repeat(60), ERROR_USERNAME_KIRILICA + SEMICOLON + ERROR_PASSWORD_60_numbers}, // 1 2 3

        };
    }
}
