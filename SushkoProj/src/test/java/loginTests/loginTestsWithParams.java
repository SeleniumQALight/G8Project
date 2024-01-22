package loginTests;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static data.TestData.*;

@RunWith(JUnitParamsRunner.class)
public class loginTestsWithParams extends BaseTest {
    @Test
    @Parameters(method = "parametersForInvalidLoginTests")
    public void invalidLogin(String userName, String password) {
        pageProvider.loginPage().openLoginPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoInputPassword(password)
                .clickOnButtonSingIn()
        ;

        Assert.assertTrue("Validation message is not displayed",
                pageProvider.loginPage().isValidationMessageIsDisplayed());
    }

    public Object[][] parametersForInvalidLoginTests() {
        return new Object[][]{
                {"qaauto_wrong", "123456qwerty"},
                {"qaauto", "123456qwerty_wrong"}
        };
    }
}
