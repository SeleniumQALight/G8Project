package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//p[contains(text(), 'Is this post unique?')]")
    private WebElement uniquePostMessage;

    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    @FindBy(xpath = ".//div[@class='container py-md-5 container--narrow']/div[5]/p")
    private WebElement postBody;

    @FindBy(xpath = ".//div[@class='container py-md-5 container--narrow']/div[3]/p")
    private WebElement postNote;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        // TODO check element
        return this;
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this; // return this - because eventually we stay on this page
    }

    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public PostPage checkTextInUniquePostInfoMessage(String value) {
        checkTextInElement(uniquePostMessage, "Is this post unique? : " + value);
        return this;
    }

    public PostPage checkTextInPostTitle(String postTitleText) {
        checkTextInElement(postTitle, postTitleText);
        return this;
    }

    public PostPage checkTextInPostBody(String postBodyText) {
        checkTextInElement(postBody, postBodyText);
        return this;
    }

    public PostPage checkTextInPostNote(String noteText) {
        checkTextInElement(postNote, noteText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
