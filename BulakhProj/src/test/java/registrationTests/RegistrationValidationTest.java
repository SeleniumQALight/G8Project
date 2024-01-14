package registrationTests;

import baseTast.BaseTest;
import org.junit.Test;

public class RegistrationValidationTest extends BaseTest {

    @Test
    public void registrationValidation() {
        pageProvider.loginPage()
                .openLoginPage();
        pageProvider.loginPage()
                .fillRegistrationForm("tr", "tr", "tr")
                .clickOnButtonSingUp()
                .checkValidationMessageForUsername()
                .checkValidationMessageForEmail()
                .checkValidationMessageForPassword()
        ;

    }
}
