package loginTests;

import baseTast.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitParamsRunner.class)
public class ValidLogin extends BaseTest {

    final String ERROR_MESSAGE = "Invalid username/password.";

    @Test
    @Parameters(method = "parametersForValidationMessages")
    public void testForValidationMessage(String login, String password, String expectedMassage){
        pageProvider.loginPage().openLoginPage();
        pageProvider.loginPage().enterTextInToInputLogin(login);
        pageProvider.loginPage().enterTextInToInputPassword(password);
        pageProvider.loginPage().clickOnButtonSingIn();
        pageProvider.loginPage().checkErrorMessageInLoginForm(expectedMassage);
    }

    public Object[][] parametersForValidationMessages(){
        return new Object[][]{
                {"tr", "tanya@t.com", ERROR_MESSAGE},
                {"tanya", "tr", ERROR_MESSAGE},
                {" ", " ", ERROR_MESSAGE},
                {"qaauto", "tr@tr.com", ERROR_MESSAGE}
        };
    }


}
