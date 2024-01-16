package registrationFormTest;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class ValidationMessagesTest extends BaseTest {

    @Test
    public void validationMassagesTest(){
        pageProvider.loginPage()
                .openLoginPage()
                .getRegistrationForm()
                .enterTextIntoInputUsername("vladyslava03")
                .enterTextIntoInputEmail("tr")
                .enterTextIntoInputPassword("tr")
                .checkErrorMessages("You must provide a valid email address.;Password must be at least 12 characters.")
        ;
    }
}
