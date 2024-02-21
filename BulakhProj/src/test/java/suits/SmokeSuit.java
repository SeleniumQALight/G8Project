package suits;


import apiTests.ApiTests;
import loginTests.LoginPageWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginPageWithPageObject.class,
        CreateNewPostTest.class,
        ApiTests.class

})
public class SmokeSuit {
}
