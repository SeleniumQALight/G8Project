package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

import static libs.TestData.INVALID_DATA_FOR_SIGN_IN;

public class SignUpWithInvalidCredentials extends BaseTest {
    @Test
    public void signUpWithInvalidCredentials() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputUserName(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().enterTextInToInputEmail(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().enterTextInToInputPasswordForSignUp(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().clickOnButtonSignUp();
        pageProvider.loginPage().isValidationMessageForUserNameFieldVisible();
        pageProvider.loginPage().isValidationMessageForEmailFieldVisible();
        pageProvider.loginPage().isValidationMessageForPasswordFieldVisible();
    }
}
