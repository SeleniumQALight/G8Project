package registerFormTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class RegisterFormMessagesTest extends BaseTest {
    @Test
    public void RegisterFormMessagesTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextIntoInputUsernameRegister("tr");
        pageProvider.loginPage().enterTextIntoInputEmailRegister("tr");
        pageProvider.loginPage().enterTextIntoInputPasswordRegister("tr");
        pageProvider.loginPage().clickOnButtonSignUpRegister();


        Assert.assertTrue("Button Sign Up is visible", pageProvider.loginPage().isButtonSignUpVisible());
        Assert.assertTrue("Error message is visible for username", pageProvider.loginPage().isErrorMessageVisibleForUsernameInput());
        Assert.assertTrue("Error message is visible for email", pageProvider.loginPage().isErrorMessageVisibleForEmailInput());
        Assert.assertTrue("Error message is visible for password", pageProvider.loginPage().isErrorMessageVisibleForPasswordInput());

    }
}
