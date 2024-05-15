package suits;


import apiTests.ApiTests;
import loginTests.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import createPost.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        ApiTests.class
})
public class SmokeSuit {
}
