package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParrentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

     HeaderElement headerElement;
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;


    @FindBy(xpath = ".//h2")
    private WebElement textPostTitle;

    @FindBy(xpath = ".//div[@class='body-content' and p[not(i)]]")
    private WebElement textPostBody;

    @FindBy(xpath = ".//div[@class='body-content']/p/i")
    private WebElement textOfVisibilityOfMessage;

    @FindBy(xpath = ".//*/p[contains(text(), 'Is this post unique?')]")
    private WebElement textIsThisPostUnique;

    HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
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

    public PostPage checkTitleTextInCreatedPost(String expectedTitleText) {
        checkTextInElement(textPostTitle, expectedTitleText);

        return this;
    }

    public PostPage checkBodyTextInCreatedPost(String expectedBodyText) {
        checkTextInElement(textPostBody, expectedBodyText);
        return this;
    }

    public PostPage checktextOfVisibilityOfMessage(String expectedBodyText) {
        checkTextInElement(textOfVisibilityOfMessage, "Note: This post was written for " + expectedBodyText);
        return this;
    }


    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }


    public PostPage checkTextThisPostUnique(String postUniqueCheckboxStatus) {
        if (postUniqueCheckboxStatus.toUpperCase().equals("YES")) {
            checkTextInElement(textIsThisPostUnique, "Is this post unique? : yes");
            return this;
        } else if (postUniqueCheckboxStatus.toUpperCase().equals("NO")) {
            checkTextInElement(textIsThisPostUnique, "Is this post unique? : no");
            return this;
        } else
            logger.error("Wrong parameter!!! status should be Yes or No");
        Assert.fail("Wrong parameter!!! status should be Yes or No");
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
