package registrationFormTest;

import baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.*;
public class RegistrationFormTest extends BaseTest {
    @Test
    public void checkIsValidationMessagesArePresent(){
        pageProvider.loginPage()
                .openLoginPage()
                .getRegistrationForm()
                .fillRegistrationFormWithInvalidCreds()
                .checkIsValidationMessageDisplayedForUsernameField()
                .checkIsValidationMessageDisplayedForEmailField()
                .checkIsValidationMessageDisplayedForPasswordField();
    }

    @Test
    public void validationMessagesArePresentWithTabAndEnterButtons() {
        pageProvider.loginPage()
                .openLoginPage()
                .getRegistrationForm()
                .tabToUserNameField()
                .enterTextIntoInputUsername("sv")
                .tabToEmailField()
                .enterTextIntoInputEmail("sv")
                .tabToPasswordField()
                .enterTextIntoInputPassword("sv")
                .clickOnButtonSingUpWithEnterButton()
        ;

        pageProvider.loginPage()
                .getRegistrationForm()
                .checkErrorMessages( ERROR_USERNAME + SEMICOLON + ERROR_PASSWORD + SEMICOLON + ERROR_USERNAME);
    }
}