package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{

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

    public MyProfilePage checkIsRedirectedToMyProfilePage() {
        checkCurrentUrlWithPattern();
        //TODO check is unique element present
        return this;
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle,
                1, getPostsList(postTitle).size());
        logger.info("Needed post is present");
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        int cycleCount = 0;
        int MAX_POST_COUNT = 100;
        while (!postsList.isEmpty() && cycleCount < MAX_POST_COUNT){
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectedToPostPage()
                    .clickOnTrashIcon()
                    .checkIsRedirectedToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            cycleCount++; // cycleCount = cycleCount + 1;
        }
        if (cycleCount >= 100){
            Assert.fail("There are more than 100 posts with title " + postTitle
                    + "or delete button was not found");
        }
        return this;
    }

    public MyProfilePage clickOnPostWithTitle(String postTitle) {
//        clickOnElement(getPostsList(postTitle).get(0));  //list can't be empty
//        clickOnElement(webDriver.findElement(By.xpath(
//                String.format(postTitleLocator, postTitle)
//        )));// findElement can return exception if element not found
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts is not equal", numberOfPosts, postsList.size());
        return this;
    }
}
