package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.INVALID_SIGNUP_UI;

public class SignUpInvalidTest extends BaseTest {
    @Test
    public void signUpInvalidCheckValidationMessages() {
        pageProvider.loginPage()
                .openLoginPage();
        pageProvider.loginPage()
                .enterTextIntoInputUsernameRegister(INVALID_SIGNUP_UI)
                .enterTextIntoInputEmailRegister(INVALID_SIGNUP_UI)
                .enterTextIntoInputPasswordRegister(INVALID_SIGNUP_UI)
                .clickOnButtonSignUp();
        pageProvider.loginPage().checkUsernameValidationMessage("Username must be at least 3 characters.")
                .checkEmailValidationMessage("You must provide a valid email address.")
                .checkPasswordValidationMessage("Password must be at least 12 characters.");
    }
}
