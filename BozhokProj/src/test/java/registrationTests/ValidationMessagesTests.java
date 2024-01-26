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
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_USERNAME_TAKEN = "This username is already taken.";
    final String ERROR_UNVALID_EMAIL = "You must provide a valid email address.";
    final String ERROR_USERNAME_ONLY_LETTERS = "Username can only contain letters and numbers.";
    final String ERROR_PASSWORD_MORE_60_SYMBOLS = "Password cannot exceed 50 characters.";
    final String ERROR_USERNAME_PASSWORD_LOGIN  = "Invalid username/password.";
    final String SEMICOLON = ";";

    @Test
    @Parameters(method = "parametersForValidationMessageTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextRegistrationUserNameField(userName);
        pageProvider.loginPage().enterTextRegistrationEmailField(email);
        pageProvider.loginPage().enterTextRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
    }

    public Object[][] parametersForValidationMessageTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD},
                {"tr", "tr@tr.com", "tr", ERROR_USERNAME + SEMICOLON + ERROR_PASSWORD},
                {"qaauto", "tr@tr.com", "tr", ERROR_USERNAME_TAKEN + SEMICOLON
                        + ERROR_PASSWORD},
                {"qaauto", "trtr.com", "tr", ERROR_USERNAME_TAKEN + SEMICOLON + ERROR_UNVALID_EMAIL + SEMICOLON
                        + ERROR_PASSWORD},
                {"taras", "trtr", "tr", ERROR_UNVALID_EMAIL + SEMICOLON
                        + ERROR_PASSWORD},
                {"тест", "trtr", "tr", ERROR_USERNAME_ONLY_LETTERS + SEMICOLON
                        + ERROR_UNVALID_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "trtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtr",
                        ERROR_PASSWORD_MORE_60_SYMBOLS},
                {"tr", "tr@tr.com", "trtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtrtr",
                        ERROR_USERNAME + SEMICOLON + ERROR_PASSWORD_MORE_60_SYMBOLS},
        };
    }

    @Test
    @Parameters(method = "parametersForUnValidMessageInLoginTest")
    public void unValidMessageInLoginTest(String userName, String password, String ERROR_USERNAME_PASSWORD_LOGIN) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputLogin(userName);
        pageProvider.loginPage().enterTextIntoInputPassword(password);
        pageProvider.loginPage().clickOnButtonSignIn();
        pageProvider.loginPage().checkErrorsMessagesInLogin(ERROR_USERNAME_PASSWORD_LOGIN);
    }

    public Object[][] parametersForUnValidMessageInLoginTest() {
        return new Object[][]{
                {"qaauto", "tr", ERROR_USERNAME_PASSWORD_LOGIN},
                {"tr", "123456qwerty, ", ERROR_USERNAME_PASSWORD_LOGIN},
                {"tr", "tr", ERROR_USERNAME_PASSWORD_LOGIN},
        };
    }
}


