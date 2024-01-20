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

    @Test
    public void loginAndSignOut(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .getHeader()
                .checkIsButtonSignOutVisible()
        ;

        pageProvider.loginPage()
                .openLoginPageInNewTabWithJS(1)
        ;

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();
        pageProvider.loginPage().switchToMainTab();

        pageProvider.homePage().getHeader().checkIsButtonSignOutVisible();

        pageProvider.homePage()
                .getHeader()
                .clickOnButtonSignOut()
        ;

        pageProvider.homePage().getHeader().checkIsButtonSignOutInvisible();

        pageProvider.loginPage().switchToTab(1);
        pageProvider.loginPage().refreshPage();

        pageProvider.homePage().getHeader().checkIsButtonSignOutInvisible();
    }
}