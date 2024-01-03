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
                .checkErrorsMessages("Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.")
        ;

    }
}
