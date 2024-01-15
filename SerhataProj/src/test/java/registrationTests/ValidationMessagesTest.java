package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

import static libs.TestData.INVALID_DATA_FOR_SIGN_IN;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void validationMessagesTests() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputUserName("petro");
        pageProvider.loginPage().enterTextInToInputEmail(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().enterTextInToInputPasswordForSignUp(INVALID_DATA_FOR_SIGN_IN);
        pageProvider.loginPage().checkErrorMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");

    }

}
