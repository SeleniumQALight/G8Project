package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class MyProfilePage extends ParentPage {
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check is unique element present
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                1, getPostsList(postTitle).size());
        return this;
    }

//    public MyProfilePage checkPostWithTitle(String postTitle) {
//        Assert.assertThat("Number of posts with title " + postTitle, postTitle,getPostsList(po));
//                1, getPostsList(postTitle).size());
//        return this;
//    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        int counter = 0;
        while (!postsList.isEmpty() && counter < 100) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++;
        }
        if (counter >= 100) {
            Assert.fail("There are more than 100 posts with title " + postTitle);
        }
        return this;
    }

    public PostPage clickOnPostWithTitle(String postTitle) {
        // clickOnElement(getPostsList(postTitle).get(0));  // list can not be empty

//
        clickOnElement(String.format(postTitleLocator, postTitle));
        return new PostPage(webDriver);
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts", numberOfPosts, postsList.size());
        return this;
    }
}
