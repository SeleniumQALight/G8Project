package registrationFormTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static libs.TestData.*;

@RunWith(JUnitParamsRunner.class)
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
                {"vladyslava03", "sushko@gmail.com", "sv", ERROR_PASSWORD}
        };
    }
}
