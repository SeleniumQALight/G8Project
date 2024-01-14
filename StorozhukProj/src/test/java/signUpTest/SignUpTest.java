package signUpTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void validationMessagesInRegistrationForm() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoRegistrationUserNameField ("tr");
        pageProvider.loginPage().enterTextIntoRegistrationEmailField("tr");
        pageProvider.loginPage().enterTextIntoInputPassword("tr");
        pageProvider.loginPage().clickOnButtonSignUp();
        Assert.assertTrue("Validation Message For User Name Register Input is not visible", pageProvider.loginPage().isValidationMessageForUserNameRegisterInputDisplayed());
        Assert.assertTrue("Validation Message For Email Register Input is not visible", pageProvider.loginPage().isValidationMessageForEmailRegisterInputDisplayed());
        Assert.assertTrue("Validation Message For Password Register Input is not visible", pageProvider.loginPage().isValidationMessageForPasswordRegisterInputDisplayed());


    }
}
