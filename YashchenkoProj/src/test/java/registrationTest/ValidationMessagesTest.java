package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)
public class ValidationMessagesTest extends BaseTest {
    final String ERROR_USERNAME_1 = "Username must be at least 3 characters.";
    final String ERROR_USERNAME_2 = "Username can only contain letters and numbers.";
    final String ERROR_EMAIL_1 = "You must provide a valid email address.";
    final String ERROR_PASSWORD_1 = "Password must be at least 12 characters.";
    final String ERROR_PASSWORD_2 = "Password cannot exceed 50 characters.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoRegistrationInputLogin(userName);
        pageProvider.getLoginPage().enterTextIntoRegistrationInputEmail(email);
        pageProvider.getLoginPage().enterTextIntoRegistrationInputPassword(password);
        pageProvider.getLoginPage().checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"tim", "123", "123", ERROR_EMAIL_1 + SEMICOLON + ERROR_PASSWORD_1},
                {"tim", "tim@gmail.com", "123", ERROR_PASSWORD_1},
                {"test@gmail.com", "tim@gmail.com", "123", ERROR_USERNAME_2 + SEMICOLON + ERROR_PASSWORD_1},
                {"11", "tim@gmail.com", "1234567890123456789012345678901234567890123456789012345678901234567890", ERROR_USERNAME_1 + SEMICOLON + ERROR_PASSWORD_2}
        };
    }
}
