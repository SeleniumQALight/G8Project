package postTest;

import BaseTest.BaseTest;
import org.junit.Ignore;
import org.junit.Test;

public class CreateNewPostTest extends BaseTest {
    @Test
    @Ignore
    public void createNewPost() {
        pageProvider.loginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsredirectToHomePage()
        ;
    }
}
