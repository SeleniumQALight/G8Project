package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends ParentPage{
    private String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check url
        //TODO check is unique element present
        return this;
    }

    private List<WebElement> getPostsList(String postTitle) {
        return webDriver.findElements(By.xpath(  //не викине ексепшн,якщо не знайде елемент. Поверне пустий список
                        String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Number of posts with title " + postTitle,
                1, getPostsList(postTitle).size());
        return this;
    }
}