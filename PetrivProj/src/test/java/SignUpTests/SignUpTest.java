package SignUpTests;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void validationMessagesInRegistrationForm() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField("tr");
        pageProvider.loginPage().enterTextIntoRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextIntoRegistrationPasswordField("tr");
        pageProvider.loginPage().clickOnButtonSignUp();
        Assert.assertTrue("Validation Message For User Name Register Input is not visible", pageProvider.loginPage().isValidationMessageForUserNameRegisterInputDisplayed());
        pageProvider.loginPage().checkTextInValidationMessageForUserNameRegisterInput("Username must be at least 3 characters.");
        Assert.assertTrue("Validation Message For Email Register Input is not visible", pageProvider.loginPage().isValidationMessageForEmailRegisterInputDisplayed());
        pageProvider.loginPage().checkTextInValidationMessageForEmailRegisterInput("You must provide a valid email address.");
        Assert.assertTrue("Validation Message For Password Register Input is not visible", pageProvider.loginPage().isValidationMessageForPasswordRegisterInputDisplayed());
        pageProvider.loginPage().checkTextInValidationMessageForPasswordRegisterInput("Password must be at least 12 characters.");
    }
}
