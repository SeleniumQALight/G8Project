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
        // TODO check is unique elements
        return this;
    }

    private List<WebElement> getPostsList (String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle, 1, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostWhilePresent(String post_title) {
        List<WebElement> postsList = getPostsList(post_title);
        int counter = 0;
        final int MAX_POST_COUNT = 100;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + post_title + " was deleted");
            postsList = getPostsList(post_title);
        }
        if (counter >= MAX_POST_COUNT) {
             Assert.fail("There are more than 100 posts with title " + post_title);
        }
        return this;
    }

    public MyProfilePage clickOnPostWithTitle(String postTitle) {
        //clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ", numberOfPosts, postsList.size());
        return this;
    }
}
