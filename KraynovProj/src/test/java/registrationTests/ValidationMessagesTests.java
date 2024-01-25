package registrationTests;

import baseTest.BaseTest;
import data.TestData;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";",
    PASSWORD_CANNOT_EXCEED_50 = "Password cannot exceed 50 characters.",
    NAME_FIELD_CAN_ONLY_CONTAIN_LETTERS_AND_NUMBERS = "Username can only contain letters and numbers.";

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTest(String username, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField(username);
        pageProvider.loginPage().enterTextIntoRegistrationEmailField(email);
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"anton", "ant", "ant", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"anton", "ant@ant.com", "ant", ERROR_PASSWORD},
                {"anton", "ant", "ant", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"anton", "antttt@gmail.com", "1", ERROR_PASSWORD},
                {"antonn", "ayyntttt@gmail.com", "1".repeat(60), PASSWORD_CANNOT_EXCEED_50},
                {"АнтоНН", "rtytrrrr@gmail.com", TestData.VALID_PASSWORD_UI, NAME_FIELD_CAN_ONLY_CONTAIN_LETTERS_AND_NUMBERS}
        };
    }
}