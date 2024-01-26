package suits;


import loginTests.LoginPageWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPageWithPageObject.class,
        CreateNewPostTest.class

})
public class SmokeSuit {
}
