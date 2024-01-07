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

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check url
        //TODO check is unique element present
        return this;
    }

    private List<WebElement> getListOfPosts(String postTitle) {
        return webDriver.findElements(By.xpath(
                String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                1, getListOfPosts(postTitle).size());
        return this;
    }

    public PostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(getListOfPosts(postTitle).get(0)); //чи ок що він буде клікати завжди на перший елемент?
        return new PostPage(webDriver);
    }
}
