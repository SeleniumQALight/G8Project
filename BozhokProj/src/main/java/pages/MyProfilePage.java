package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage {

    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectOnMyProfilePage() {
        // TODO check url
        // TODO check unique element present
        return this;
    }

    // findElements - вертає список елементів, не викине Exception якщо елемент не знайдено
    private List<WebElement> getPostList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

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
}
