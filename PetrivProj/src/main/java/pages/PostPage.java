package pages;

import io.qameta.allure.Step;
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
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;
    @FindBy(xpath = ".//form[contains(@action, '/post/')]//button")
    private WebElement buttonSaveUpdates;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    @Step
    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        // TODO check element
        return this;
    }

    @Step
    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this; // return this - because eventually we stay on this page
    }
    @Step
    public PostPage checkTextInSuccessMessage(String text) {
        checkTextInElement(successMessage, text);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }
    @Step
    public PostPage checkTextInUniquePostInfoMessage(String value) {
        checkTextInElement(uniquePostMessage, "Is this post unique? : " + value);
        return this;
    }
    @Step
    public PostPage checkTextInPostTitle(String postTitleText) {
        checkTextInElement(postTitle, postTitleText);
        return this;
    }
    @Step
    public PostPage checkTextInPostBody(String postBodyText) {
        checkTextInElement(postBody, postBodyText);
        return this;
    }
    @Step
    public PostPage checkTextInPostNote(String noteText) {
        checkTextInElement(postNote, noteText);
        return this;
    }
    @Step
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
    @Step
    public PostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return this;
    }
    @Step
    public void enterTextIntoInputTitle(String text) {
        enterTextIntoInput(inputTitle, text);
    }
    @Step
    public PostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }
}
