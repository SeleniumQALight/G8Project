import baseTest.BaseTest;
import libs.ErrorMessage;
import libs.TestData;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void registrationFormValidation() {
        pageProvider.homePage().openHomePage();
        pageProvider.registrationForm().fillForm(TestData.INVALID_DATA, TestData.INVALID_DATA, TestData.INVALID_DATA);
        pageProvider.registrationForm().clickOnSignInForOurAppButton();
        pageProvider.registrationForm().checkErrorMessage(0, ErrorMessage.USER_NAME_MUST_HAS_3_CHARACTERS);
        pageProvider.registrationForm().checkErrorMessage(1, ErrorMessage.YOU_MUST_PROVIDE_VALID_ADDRESS);
        pageProvider.registrationForm().checkErrorMessage(2, ErrorMessage.PASSWORD_MUST_BE_12);


    }
}
