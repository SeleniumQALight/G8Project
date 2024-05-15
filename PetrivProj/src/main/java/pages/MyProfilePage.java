package pages;

import io.qameta.allure.Step;
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

    @Step
    public MyProfilePage checkIsRedirectToMyProfilePage(){
        checkUrlWithPattern();
        // TODO: check is unique element present
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle))); // method findElements() doesn't throw exception if element not found
    }

    @Step
    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Number of posts with title " + postTitle, 1, getPostsList(postTitle).size());
        return this;
    }

    @Step
    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        int counter = 0;
        int MAX_POST_COUNT = 100;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) { // check if list is not empty (return false if list is not empty)
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle); // update list of posts (check what posts left)
            counter++;
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more than 100 posts with title " + postTitle);
        }
        return this;
    }

    @Step
    public MyProfilePage clickOnPostWithTitle(String postTitle) {
        // clickOnElement(getPostsList(postTitle).get(0)); // list can't be empty
        // clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle)))); // if element not found, throw exception (findElement()) can return exception
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }

    @Step
    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ", numberOfPosts, postsList.size());
        return this;
    }
}
