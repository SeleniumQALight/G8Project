package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends ParentPage  {

    @FindBy(xpath = "//input[@id='post-title']")
    private WebElement titleField;

    @FindBy(xpath = "//a[text()='« Back to post permalink']")
    private WebElement backToPostPermalink;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement saveUpdatesButton;

    @FindBy(xpath = "//*[contains(@class,'alert-success')]")
    private WebElement successMessage;

    public EditPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-z0-9]*/edit";
    }

    public EditPage checkIsRedirectToEditPostPage() {
        checkUrlWithPatternUrl();
        checkIsLinkBackToPostPermalinkDisplayed();
        return this;
    }

    public PostPage clickLinkBackToPostPermalink() {
        clickOnElement(backToPostPermalink);
        return new PostPage(webDriver);
    }

    public EditPage clickOnSaveUpdatesButton() {
        clickOnElement(saveUpdatesButton);
        return new EditPage (webDriver);
    }

    public EditPage enterTitleInToInputTitle (String title) {
        enterTextIntoInput(titleField, title);
        return this;
    }

    public EditPage checkIsLinkBackToPostPermalinkDisplayed() {
        checkIsElementVisible(backToPostPermalink);
        return this;
    }

    public EditPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public EditPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }
}
