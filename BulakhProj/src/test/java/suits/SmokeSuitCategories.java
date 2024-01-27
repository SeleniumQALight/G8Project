package suits;


import categories.SmokeTestFilter;
import loginTests.LoginPageWithPageObject;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postsTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTests;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginPageWithPageObject.class,
        CreateNewPostTest.class,
        ValidationMessagesTests.class

})
public class SmokeSuitCategories {
}
