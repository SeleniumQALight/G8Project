package SignUpTests;

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
    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorMessages(expectedMessages);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tssr@te.com", "tr", ERROR_PASSWORD},
        };
    }

    @Test
    public void validationMessagesTestUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(5);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputActions("tr");
        pageProvider.loginPage().pressEnterKey();
        pageProvider.loginPage().checkErrorMessages("Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.");
    }
}
