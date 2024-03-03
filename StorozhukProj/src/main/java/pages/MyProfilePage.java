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
        return "/profile/[a-zA-Z0-9]+"; // "/pr
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        // TODO check is unique element present
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle, 1, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        int counter = 0;
        final int MAX_ITERATION = 100;
        while (!postsList.isEmpty() && counter < MAX_ITERATION) {
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeletePostButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++;  // counter = counter + 1;
        }
        if (counter >= MAX_ITERATION) {
            Assert.fail("There are more than 100 posts with title " + postTitle);
        }
        return this;
    }

    public MyProfilePage clickOnPostWithTitle(String postTitle) {
        //  clickOnElement(getPostsList(postTitle).get(0));

        //   clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));//findElement can return exception if element not found

        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }

    public MyProfilePage checkNumberOfTotalPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ",numberOfPosts, postsList.size());
        return this;
    }
}
