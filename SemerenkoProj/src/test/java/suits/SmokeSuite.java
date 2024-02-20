package suits;


import LoginTests.LoginTestWithPageObject;
import apiTests.ApiTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTests.CreateNewPostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
