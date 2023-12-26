package postsTests;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;


public class CreateNewPostTest extends BaseTest {

    @Test
    @Ignore
    public void createNewPostTest() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
        ;
    }


}
