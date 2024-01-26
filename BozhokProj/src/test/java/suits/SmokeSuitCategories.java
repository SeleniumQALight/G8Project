package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTests;

@RunWith(Categories.class)
@Categories.ExcludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTests.class
})
public class SmokeSuitCategories {

}
