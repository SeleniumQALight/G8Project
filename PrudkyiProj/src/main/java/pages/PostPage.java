package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    HeaderElement headerElement;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public PostPage checkIsRedirectToPostPage() {
        //TODO check url
        //TODO check is element present
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }
    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }
}
