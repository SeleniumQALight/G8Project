package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage{

    private String postTitleLocator = "//a[contains(@class,'list-group-item')]/strong[text()='%s']";

    private List<WebElement> getPostList(String postTitle) {
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle, int expectedResult){
        Assert.assertEquals("Count of post with title " + postTitle, expectedResult,getPostList(postTitle).size());
        return this;
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {

        return this;
    }
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
}
