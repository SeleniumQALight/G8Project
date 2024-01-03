package signUpTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest extends BaseTest {


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

}


