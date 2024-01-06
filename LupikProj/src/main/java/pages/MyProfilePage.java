package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyProfilePage extends  ParrentPage {

    public String postTitleLocator = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


   public MyProfilePage checkIsRedirectToMyProfilePage(){
        //TODO check url
       //TODO check is unique element present
        return this;
    }

    private List <WebElement> getPostList(String postTitle){
        return webDriver.findElements(By.xpath(String.format(postTitleLocator, postTitle)));
    }

    public MyProfilePage checkPostWithTitleIsPresent(String postTitle) {
        Assert.assertEquals("Count of posts with title " + postTitle, 1, getPostList(postTitle).size());
        return this;
    }
}
