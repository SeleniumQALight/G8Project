package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = ".//*[text()='%s']";
    @FindBy(xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        //TODO check is unique element present
        return this;
    }

    private List<WebElement> getListOfPosts(String postTitle) {
        return webDriver.findElements(By.xpath(
                String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                1, getListOfPosts(postTitle).size());
        return this;
    }

    public PostPage clickOnPostWithTitle(String postTitle) {
        //  clickOnElement(getListOfPosts(postTitle).get(0));//list can't be empty
        //  clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));//findElement can return null
        clickOnElement(String.format(postTitleLocator, postTitle));
        return new PostPage(webDriver);
    }

    public MyProfilePage deletePostWhilePresent(String postTitle) {
        List<WebElement> postsList = getListOfPosts(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getListOfPosts(postTitle);
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than 100 posts with title " + postTitle);
        }
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ", numberOfPosts, postsList.size());
        return this;
    }
}
