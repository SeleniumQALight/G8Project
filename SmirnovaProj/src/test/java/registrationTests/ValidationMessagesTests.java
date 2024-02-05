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
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";
    final String ERROR_USERNAME_CYRILLIC = "Username can only contain letters and numbers.";
    final String ERROR_PASSWORD_LONG= "Password cannot exceed 50 characters.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String username, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage()
                .enterTextIntoInputUsernameRegister(username)
                .enterTextIntoInputEmailRegister(email)
                .enterTextIntoInputPasswordRegister(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessages);
    }

    private Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras","tr@tr.com","tr", ERROR_PASSWORD},
                {"тест","tr@tr.com","tr", ERROR_USERNAME_CYRILLIC + SEMICOLON + ERROR_PASSWORD},
                {"taras","tr@tr.com","3713uywqieyhquwejhjjkwqhruqwreyirewyuuryiuuyyuuuuuu", ERROR_PASSWORD_LONG}
        };
    }


}
