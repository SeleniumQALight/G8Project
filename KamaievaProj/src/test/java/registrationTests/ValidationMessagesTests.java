package registrationTests;

import baseTest1.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {

    final String ERROR_MESSAGE_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_MESSAGE_EMAIL = "You must provide a valid email address.";
    final String ERROR_MESSAGE_PASSWORD = "Password must be at least 12 characters.";

    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String username, String email, String password, String errorMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputUsernameRegister(username);
        pageProvider.getLoginPage().enterTextIntoInputEmailRegister(email);
        pageProvider.getLoginPage().enterTextIntoInputPasswordRegister(password);
        pageProvider.getLoginPage().checkErrorsMessages(errorMessage);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_MESSAGE_EMAIL + SEMICOLON + ERROR_MESSAGE_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_MESSAGE_PASSWORD}
        };
    }
}
