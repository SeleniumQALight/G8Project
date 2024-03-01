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
    protected String getRelativUrl() {
        return "/profile/[a-zA-z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        // TODO check is page title correct
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle,
                1, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        int counter = 0;
        final int MaxPostsCount = 100;
        while (!postsList.isEmpty() && counter < MaxPostsCount) {
            clickOnElement(postsList.get(0));// click on first post in the list клікнути на перший пост в списку
            new PostPage(webDriver)
                    .checkIsRedirectedToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++;//counter = counter + 1;
        }
        if (counter >= MaxPostsCount) {
            Assert.fail("There are more then 100 posts with title " + postTitle
                    + " or Delete button does not work");
        }
        return this;
    }

    public MyProfilePage checkOnPostWithTitle(String postTitle) {
//        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));//findElement - шукає елемент can return null

    //    clickOnElement(getPostsList(postTitle).get(0));//click on first post in the list клікнути на перший пост в списку
      clickOnElement(String.format(postTitleLocator, postTitle)); //findElement - шукає елемент can return null
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts", numberOfPosts, postsList.size());
        return this;
    }
}

