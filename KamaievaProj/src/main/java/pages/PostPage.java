package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check url
        //TODO Check if we are on the Post page
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
}
