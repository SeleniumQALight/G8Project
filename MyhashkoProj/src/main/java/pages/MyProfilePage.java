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
    private List<WebElement> postList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public  MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        // TODO Check is unique element present
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(
                String.format(postTitleLocator, postTitle)
        ));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle,
                1, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postsList = getPostsList(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100;
        while (!postsList.isEmpty() && counter < MAX_POST_COUNT){
            clickOnElement(postsList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postsList = getPostsList(postTitle);
            counter++; // counter = counter + 1;
        }
        if (counter >= MAX_POST_COUNT){
            Assert.fail("There are more then 100 posts with title " + postTitle
                    + "or Delete button was not found");
        }
        return this;
    }

    public MyProfilePage clickOnPostWithTitle(String postTitle) {
     //   clickOnElement(getPostsList(postTitle).get(0)); // list can  not be empty!!

//        clickOnElement(webDriver.findElement(By.xpath(
//                String.format(postTitleLocator, postTitle)))); // findElement can return exception if element not found
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }

    public MyProfilePage checkNumberOfPosts(int numberOfPosts) {
        Assert.assertEquals("Number of posts ", numberOfPosts, postList.size());
        return this;
    }
}
