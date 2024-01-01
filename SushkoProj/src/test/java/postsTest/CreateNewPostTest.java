package postsTest;

import baseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    @Ignore
    public void createNewPost(){
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCreds()
                .checkIsRedirectToHimePage()
        ;
    }
}
