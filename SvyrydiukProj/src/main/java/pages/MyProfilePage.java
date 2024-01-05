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

    //check if we are on my profile page
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        // TODO check url
        //TODO check is unique element present
        return this;
    }

    //check if post with title is present
    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)  //no exception if no elements found
        ));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " +postTitle, 1, getPostsList(postTitle).size());
        return this;
    }
}
