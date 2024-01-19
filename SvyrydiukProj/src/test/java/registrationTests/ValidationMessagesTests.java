package registrationTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

public class ValidationMessagesTests extends BaseTest {
    final String ERROR_USERNAME = "Username must be at least 3 characters.";
    final String ERROR_EMAIL = "You must provide a valid email address.";
    final String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final String SEMICOLON = ";";

    @Test
    public void signUpTestCheckValidationMessage() {


        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegistration("ab");
        pageProvider.loginPage().enterTextIntoInputEmailRegistration("em");
        pageProvider.loginPage().enterTextIntoInputPasswordRegistration("12");
        pageProvider.loginPage().clickOnButtonSignUp();
        Assert.assertTrue("Validation message for UserName is not visible"
                , pageProvider.loginPage().isValidationMessageForUserNameFieldVisible());
        pageProvider.loginPage().checkTextValidationMessageForUserNameRegistrationField("Username must be at least 3 characters.");
        Assert.assertTrue("Validation message for Email is not visible"
                , pageProvider.loginPage().isValidationMessageForEmailFieldVisible());
        pageProvider.loginPage().checkTextValidationMessageForEmailRegistrationField("You must provide a valid email address.");
        Assert.assertTrue("Validation message for password is not visible"
                , pageProvider.loginPage().isValidationMessageForPasswordFieldVisible());
        pageProvider.loginPage().checkTextValidationMessageForPasswordRegistrationField("Password must be at least 12 characters.");

    }

    @Test
    @Parameters(method = "parametersForValidationMessagesTests")
    public void validationMessagesTests(String userName, String email, String password, String expectedMessage) {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegistration(userName);
        pageProvider.loginPage().enterTextIntoInputEmailRegistration(email);
        pageProvider.loginPage().enterTextIntoInputPasswordRegistration(password);
        pageProvider.loginPage().checkErrorsMessages(expectedMessage);

    }

    public Object[][] parametersForValidationMessagesTests() {
        return new Object[][]{
                {"taras", "tr", "tr", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr@tr.com", "tr", ERROR_PASSWORD}
        };
    }

    @Test
    public void validationMessagesTestsWithTABKey() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKey(6);
        pageProvider.loginPage().enterTextIntoInputWithActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputWithActions("tr");
        pageProvider.loginPage().pressTabKey(1);
        pageProvider.loginPage().enterTextIntoInputWithActions("tr");
        pageProvider.loginPage().pressEnterKey();
        pageProvider.loginPage().checkErrorsMessages(
                "Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.");
    }
}


