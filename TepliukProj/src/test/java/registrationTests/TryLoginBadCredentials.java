package registrationTests;

import baseTest.BaseTest;
import org.junit.Test;

public class TryLoginBadCredentials extends BaseTest {
    @Test
    public void tryLoginBadCredentials() {
        //input invalid data to Username, Email and Password fields
        pageProvider.loginPage().openLoginPageAndFillLoginFormWithInvalidCred()
           //     .checkIsRedirectToLoginPage()
                .checkIsErrorMessageDisplayed()

//2024-10-01 21:47:29   INFO [           LoginPage:55  ] : Element  is displayed -> true
//2024-10-01 21:47:29   INFO [           LoginPage:55  ] : Element  is displayed -> true
//2024-10-01 21:47:30   INFO [           LoginPage:55  ] : Element  is displayed -> true
// не можу зрозуміти як відображати назву кожного елемента, який відображається



        ;
    }
}