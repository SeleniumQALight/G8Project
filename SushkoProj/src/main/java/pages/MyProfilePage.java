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

    @Override
    protected String getRelativeUrl() {
        return "/profile/[a-zA-Z0-9]*";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkUrlWithPattern();
        // TODO check is unique element present
        return this;
    }

    //findElements - не буде ексепшену, якщо не знайде нічого, поверне порожній список
    //findElement -  поверне ексепшн, якщо нічого не знайде
    private List<WebElement> getPostsList(String postTitle){
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }
    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle, 1, getPostsList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postList = getPostsList(postTitle);
        int counter = 0;
        final int MAX_POST_COUNT = 100;
        while (!postList.isEmpty() && counter < MAX_POST_COUNT){
            clickOnElement(postList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
            ;
            logger.info("Post with title " + postTitle + " was deleted");
            postList = getPostsList(postTitle);
            counter++;
        }

        if (counter >= MAX_POST_COUNT){
            Assert.fail("There are more then 100 posts with title " + postTitle);
        }

        return this;
    }
}
