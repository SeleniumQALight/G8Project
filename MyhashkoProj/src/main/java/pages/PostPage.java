package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = "//div/p[contains(text(),'Is this post unique')]")
    private WebElement checkBoxIsThisPostUnique;

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        // TODO check url
        // TODO Check element
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

    public PostPage chekStatusOfCheckBoxIsThisPostUniqueOnPostPage(String text) {
        if (text.equals("yes")) {
            checkTextInElement(checkBoxIsThisPostUnique, "Is this post unique? : yes");
            logger.info("This post unique");
        } else if (text.equals("no")) {
            checkTextInElement(checkBoxIsThisPostUnique, "Is this post unique? : no");
            logger.info("This post not unique");
        } else {
            logger.error("Can not answer if this post unique");
            Assert.fail("Can not answer if this post unique");
        }
        return this;
    }
}
