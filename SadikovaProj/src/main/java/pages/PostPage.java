package pages;

import org.apache.hc.core5.http.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.List;

public class PostPage extends ParentPage {

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    private WebElement postCreatedBlock;

    private pages.elements.HeaderElement headerElement;


    public PostPage checkIsRedirectToPostPage() {
        //ToDo check url
        //Todo check is unique element present
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayes() {
        checkIsElementVisible(postCreatedBlock);
        return this;
    }

    public PostPage checkTextPresent(String expectedText) {
        checkTextInElement(postCreatedBlock, expectedText);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new pages.elements.HeaderElement(webDriver);
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }


}
