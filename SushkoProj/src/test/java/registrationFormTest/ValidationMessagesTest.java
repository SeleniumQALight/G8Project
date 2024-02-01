package registrationFormTest;

import baseTest.BaseTest;
import categories.SmokeTestsFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static data.TestData.*;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestsFilter.class)
public class ValidationMessagesTest extends BaseTest {

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMassagesTest(String userName, String email, String password, String expectedMessages) {
        pageProvider.loginPage()
                .openLoginPage()
                .getRegistrationForm()
                .enterTextIntoInputUsername(userName)
                .enterTextIntoInputEmail(email)
                .enterTextIntoInputPassword(password)
                .checkErrorMessages(expectedMessages)
        ;
    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"vladyslava03", "sv", "sv", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"vladyslava03", "sushko@gmail.com", "sv", ERROR_PASSWORD},
                {"тест", "sushko@gmail.com", "sv", ERROR_USERNAME_SYMBOLS + SEMICOLON + ERROR_PASSWORD},
                {"vladyslava03", "sushko@gmail.com", "123456789012345678901234567890123456789012345678901234567890",
                       ERROR_PASSWORD_CHARACTERS_COUNT}
        };
    }
}
