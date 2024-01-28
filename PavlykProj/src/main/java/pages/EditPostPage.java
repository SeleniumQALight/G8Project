package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//a[text()='« Back to post permalink']")
    private WebElement linkBackToPostPermalink;

    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        checkIsLinkBackToPostPermalinkDisplayed();
        return this;
    }

    public PostPage clickLinkBackToPostPermalink() {
        clickOnElement(linkBackToPostPermalink);
        return new PostPage(webDriver);
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new EditPostPage (webDriver);
    }

    public EditPostPage enterTitleInToInputTitle (String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public EditPostPage checkIsLinkBackToPostPermalinkDisplayed() {
        checkIsElementVisible(linkBackToPostPermalink);
        return this;
    }

    public EditPostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public EditPostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }
}
