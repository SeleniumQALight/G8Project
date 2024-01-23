package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {

    final String ERROR_MESSAGE_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_MESSAGE_EMAIL = "You must provide a valid email address.";
    final String ERROR_MESSAGE_PASSWORD = "Password must be at least 12 characters.";
    final String ERROR_MESSAGE_REGISTER_LENGTH_PASSWORD = "Password cannot exceed 50 characters.";
    final String ERROR_MESSAGE_REGISTER_CHARACTER_USERNAME = "Username can only contain letters and numbers.";

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
                {"taras", "tr@tr.com", "tr", ERROR_MESSAGE_PASSWORD},
                {"nataliia", " ", "§$%&/()=?*'<>#|;²³~@©«»¤¼×{}abcdefghijklmpqrstuv377", ERROR_MESSAGE_EMAIL + SEMICOLON + ERROR_MESSAGE_REGISTER_LENGTH_PASSWORD},
                {"§$%&/()=?*<abqrstuv>", "@.com", "1234567890", ERROR_MESSAGE_REGISTER_CHARACTER_USERNAME + SEMICOLON + ERROR_MESSAGE_EMAIL + SEMICOLON + ERROR_MESSAGE_PASSWORD}
        };
    }
}
