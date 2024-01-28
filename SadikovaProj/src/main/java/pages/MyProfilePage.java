package pages;

import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = "//a[contains(@class,'profile-nav-link') and contains(text(),'Posts')]")
    private WebElement postsTab;

    private String postTitleLocator = "//a[contains(@class,'list-group-item')]/strong[text()='%s']";

    private List<WebElement> getPostList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedResult) {
        Assert.assertEquals("Count of post with title " + postTitle, expectedResult, getPostList(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsTillPresent(String postTitle) {
        List<WebElement> postList = getPostList(postTitle);
        int counter = 0;
        while (!postList.isEmpty() && counter < 100) {
            clickOnElement(postList.get(0));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage();
            logger.info("Post with title " + postTitle + " was deleted");
            postList = getPostList(postTitle);


        }
        if (counter > 100) {
            Assert.fail("This post more than 100 " + postTitle);
        }
        return this;
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        containsUrl();
        clickOnElement(postsTab);
        return this;
    }

    @Override
    protected String getRelativeUrl() {
        return "/profile/";
    }

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnPostWithTitle(String postTitle){
//        clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator,postTitle)))); //find element can return exeption if element not found
//        return this;
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }
}
