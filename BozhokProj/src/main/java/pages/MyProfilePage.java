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
    public MyProfilePage checkIsRedirectOnMyProfilePage() {
        checkUrlWithPattern();
        return this;
    }

    // findElements - вертає список елементів, не викине Exception якщо елемент не знайдено
    private List<WebElement> getPostList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    @Step
    public MyProfilePage checkPostWithTitlesIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle, 1, getPostList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postsList = getPostList(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton().checkIsRedirectOnMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostList(postTitle);
//            counter++;
            postsList = getPostList(postTitle);
        }
        if (counter >= MAX_POST_COUNT) {
            Assert.fail("There are more then 100 posts with title " + postTitle);
        }
        return this;
    }

    public MyProfilePage clickOnPostWithTitle(String postTitle) {
        //clickOnElement(getPostList(postTitle).get(0)); //list can not be empty
        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));
        //findElement can return exception if element not found
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts", numberOfPosts, postsList.size());
        return this;
    }
}
