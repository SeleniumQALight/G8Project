package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage {
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check url
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
}
