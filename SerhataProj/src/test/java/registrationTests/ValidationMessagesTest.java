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
    final String SEMICOLON = ";";

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
                {"taras","tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras","tr@tr.com", "tr", ERROR_PASSWORD}
        };
    }

}
