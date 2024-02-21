package suits;

import apiTests.ApiTests;
import categories.SmokeTestFilter;
import loginTests.LoginTestAllInOneClass;
import loginTests.LoginTextWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import postsTests.CreateNewPostTest;
import registrationTests.ValidationMessagesTests;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({LoginTextWithPageObject.class, CreateNewPostTest.class, ValidationMessagesTests.class, ApiTests.class})

public class SmokeSuit {

}
