package signUpTests;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

import static libs.TestData.SMALL_INVALID_TEXT;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTestCheckValidationMessage() {
        pageProvider.loginPage()
                .enterInvalidTextInRegistrationFieldsAndClickButtonSignUp(SMALL_INVALID_TEXT)
                .checkIsValidationMessageForUserNameFieldVisible()
                .checkIsValidationMessageForEmailFieldVisible()
                .checkIsValidationMessageForPasswordFieldVisible();
    }

    @Test
    @Ignore
    public void validationMessagesTests() {
        pageProvider.loginPage().openLoginPage();
//        pageProvider.loginPage().enterTextIntoRegistrationUserNameField("test");
//        pageProvider.loginPage().enterTextIntoRegistrationEmailField("trtr");
//        pageProvider.loginPage().enterTextIntoRegistrationPasswordField("123456");
//        pageProvider.loginPage().checkErrorsMessages("You must provide a valid email address.;Password must be at least 12 characters.");

    }


}
