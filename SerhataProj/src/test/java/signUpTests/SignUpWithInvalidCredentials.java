package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.INVALID_DATA_FOR_SIGN_IN;

public class SignUpWithInvalidCredentials extends BaseTest {
    @Test
    public void signUpWithInvalidCredentials() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputUserName(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().enterTextInToInputEmail(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().enterTextInToInputPasswordForSignUp(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().clickOnButtonSignUp();
        pageProvider.loginPage().checkIsValidationMessageForUserNameFieldVisible();
        pageProvider.loginPage().checkIsValidationMessageForEmailFieldVisible();
        pageProvider.loginPage().checkIsValidationMessageForPasswordFieldVisible();
    }

    @Test
    public void invalidLoginUsingKeyboard() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().pressTabKeyNTimes(5);
        pageProvider.loginPage().enterTextInToInputLogin(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().pressTabKeyNTimes(1);
        pageProvider.loginPage().enterTextInToInputEmail(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().pressTabKeyNTimes(1);
        pageProvider.loginPage().enterTextInToInputPasswordForSignUp(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().pressEnterKey();
        pageProvider.loginPage().checkIsValidationMessageForUserNameFieldVisible();
        pageProvider.loginPage().checkIsValidationMessageForEmailFieldVisible();
        pageProvider.loginPage().checkIsValidationMessageForPasswordFieldVisible();
    }
}
