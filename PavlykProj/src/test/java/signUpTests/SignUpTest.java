package signUpTests;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.SMALL_INVALID_TEXT;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTestCheckValidationMessage() {
        pageProvider.loginPage()
                .enterInvalidTextInRegistrationFieldsAndClickButtonSignUp(SMALL_INVALID_TEXT)
                .checkIsValidationMessageForUserNameFieldVisible()
                .checkIsValidationMessageForEmailFieldVisible()
                .checkIsValidationMessageForPasswordFieldVisible();
    }
}
