import baseTest.BaseTest;
import libs.ErrorMessage;
import libs.TestData;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void registrationFormValidation() {
        pageProvider.mainPage().openMainPage();
        pageProvider.mainPage().fillForm(TestData.INVALID_DATA, TestData.INVALID_DATA, TestData.INVALID_DATA)
        .clickOnSignInForOurAppButton();
        pageProvider.mainPage().checkErrorMessage(0, ErrorMessage.USER_NAME_MUST_HAS_3_CHARACTERS);
        pageProvider.mainPage().checkErrorMessage(1, ErrorMessage.YOU_MUST_PROVIDE_VALID_ADDRESS);
        pageProvider.mainPage().checkErrorMessage(2, ErrorMessage.PASSWORD_MUST_BE_12);


    }
}
