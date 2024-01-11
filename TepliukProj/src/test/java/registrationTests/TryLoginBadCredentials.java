package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class TryLoginBadCredentials extends BaseTest {
    @Test
    public void tryLoginBadCredentials() {
        //input invalid data to Username, Email and Password fields
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithInvalidCred()
                .checkIsErrorMessageDisplayed()


        ;
    }
}