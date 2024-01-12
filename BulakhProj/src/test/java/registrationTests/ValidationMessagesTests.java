package registrationTests;

import baseTast.BaseTest;
import org.junit.Test;

public class ValidationMessagesTests extends BaseTest{
   @Test

    public void registrationValidation() {
       pageProvider.loginPage()
               .openLoginPage();
       pageProvider.loginPage().enterTextIntoRegistrationUsernameField("tanya")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
               .checkErrorMessages("You must provide a valid email address.;Password must be at least 12 characters.")




               ;

}
}
