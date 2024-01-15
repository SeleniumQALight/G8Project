package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class ValidationMessageTests extends BaseTest {

    @Test
    public void validationMessageTest(){
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillRegistrationForm("","tr", "tr");
        pageProvider.mainPage().checkErrorsMessage("You must provide a valid email address.;Password must be at least 12 characters.");

    }

}
