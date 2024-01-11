package signOutTests;

import baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void signOut(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader()
                .checkAllElementsInHeaderAreVisible()
        ;
        pageProvider.loginPage()
                .checkAllElementsFromLoginFormAreInvisible()
        ;
        pageProvider.homePage()
                .getHeader()
                .clickOnButtonSignOut()
                .checkAllElementsFromLoginFormAreVisible()
        ;
        pageProvider.homePage()
                .getHeader()
                .checkAllElementsFromHeaderAreInvisible()
        ;

    }
}