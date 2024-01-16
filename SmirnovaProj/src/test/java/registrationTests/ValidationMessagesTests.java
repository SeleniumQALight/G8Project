package registrationTests;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

import static libs.TestData.INVALID_SIGNUP_UI;
import static libs.TestData.VALID_LOGIN_UI;


public class ValidationMessagesTests extends BaseTest {
    @Test
    public void validationMessagesTest() {
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage()
                .enterTextIntoInputUsernameRegister(VALID_LOGIN_UI)
                .enterTextIntoInputEmailRegister(INVALID_SIGNUP_UI)
                .enterTextIntoInputPasswordRegister(INVALID_SIGNUP_UI);
        pageProvider.loginPage().checkErrorsMessages(
                "You must provide a valid email address.;Password must be at least 12 characters.");

    }
}
