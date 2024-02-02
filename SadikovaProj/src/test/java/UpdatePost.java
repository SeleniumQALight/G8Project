import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import libs.*;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@RunWith(JUnitParamsRunner.class)
public class UpdatePost extends BaseTest {

    private Logger logger = Logger.getLogger(getClass());

    final String LOGIN_DB = "newqaauto";

    DB_seleniumUsers db_seleniumUsers = new DB_seleniumUsers();
    String TITLE;
    String BODY;
    String VISIBILITY;
    String ALIAS;
    String DATA = Util.getDateAndTimeFormattedOnlyNumbers();


    @Before
    public void createPost() throws SQLException, ClassNotFoundException, IOException {
        Map<String, String> dataForPost = ExcelDriver.getData(ConfigProvider.configProperties.DATA_FILE(),
                "create_new_post");
        TITLE = dataForPost.get("title").replace("%s", DATA);
        BODY = dataForPost.get("body").replace("%s", DATA);
        VISIBILITY = dataForPost.get("visibility");
        pageProvider.mainPage()
                .loginToProfile(LOGIN_DB, db_seleniumUsers.getPassForLogin(LOGIN_DB));
        pageProvider.homePage().checkIsRedirectToHomePage();
        pageProvider.headerElement().clickCreatePostButton();
        pageProvider.createPostPage().checkIsRedirectToCreatePostPage();

        pageProvider.createPostPage().enterTextInTitleInInputTitle(TITLE);
        pageProvider.createPostPage().enterTextInBodyContentField(BODY);
        pageProvider.createPostPage().selectValueInDropdown(VISIBILITY);
        pageProvider.createPostPage().checkSetStateCheckbox(dataForPost.get("status"));
        pageProvider.createPostPage().clickSaveNewPostButton();
        pageProvider.postPage().checkIsSuccessMessageDisplayes();
        pageProvider.postPage().checkTextPresent(dataForPost.get("notification"))
                .checkTitleTextIsEquals(TITLE)
                .checkBodyTextIsEquals(BODY)
                .checkNoteBlockWebElementIsPresent(VISIBILITY);


        pageProvider.postPage().getHeaderElement()
                .clickOnMyProfileButton()
                .checkPostWithTitleIsPresent(TITLE, 1);
    }

    @Test
    public void updatePost() throws SQLException, ClassNotFoundException {
        ALIAS = db_seleniumUsers.getAliasByLogin(LOGIN_DB);
        pageProvider.postPage()
                .clickOnPostWithTitle(TITLE);
        pageProvider.postPage().clickOnEditButton()
                .enterTitleInToInputTitle(ALIAS)
                .clickOnSaveUpdatesButton()
                .checkIsRedirectToEditPostPage()
                .checkIsSuccessMessageDisplayed()
                .checkTextInSuccessMessage("Post successfully updated.")
                .clickLinkBackToPostPermalink();

        pageProvider.postPage().getHeaderElement()
                .clickOnMyProfileButton()
                .checkPostWithTitleIsPresent(ALIAS, 1);

    }

    @After
    public void deletePost() {
        pageProvider.homePage()
                .openHomePageAndLoginIfNeeded()
                .getheaderElement().clickOnMyProfileButton()
                .deletePostsTillPresent(ALIAS)

        ;
    }

}

