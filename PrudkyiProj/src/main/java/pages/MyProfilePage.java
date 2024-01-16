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
        // TODO check is page title correct
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle,
                1, getPostsList(postTitle).size());
        return this;
    }
    public MyProfilePage clickOnPostWithTitle(String postTitle) {
        //clickOnElement(getPostsList(postTitle).get(0));// list can be empty
       // clickOnElement(webDriver.findElement(By.xpath(String.format(postTitleLocator, postTitle))));// findelement can return exception or null
        clickOnElement(String.format(postTitleLocator, postTitle));
        return this;
    }
}