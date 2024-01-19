package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    private String locatorNoteCreatePostMessage = ".//i[text()=' Note: This post was written for ']/u[text()=' %s']";

    private String locatorStateOfUniquePost = ".//p[text()='Is this post unique? : %s']";

    private String locatorCreatedPostBody = ".//div[@class='body-content']/p[text()='%s']";

    private String locatorCreatedPostTitle = ".//h2[text()='%s']";

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    private WebElement getNoteCreatePostMessage(String text) {
        try {
            return webDriver.findElement(By.xpath(String.format(locatorNoteCreatePostMessage, text)));
        } catch (Exception e) {
            return null;
        }
    }

    private WebElement getStateOfUniquePost(String state) {
        return getElement(locatorStateOfUniquePost, state);
    }

    private WebElement getCreatedPostBody(String bodyText) {
        return getElement(locatorCreatedPostBody, bodyText);
    }

    private WebElement getCreatedPostTitle(String titleText) {
        return getElement(locatorCreatedPostTitle, titleText);
    }


    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        // TODO check element
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

    public PostPage checkIsTitleVisible(String postTitle) {
        checkIsElementVisible(getCreatedPostTitle(postTitle), "postTitle");
        return this;
    }

    public PostPage checkIsBodyVisible(String postBody) {
        checkIsElementVisible(getCreatedPostBody(postBody), "postBody");
        return this;
    }

    public PostPage checkNoteCreatePostMessage(String dropDownValue){
        checkIsElementVisible(getNoteCreatePostMessage(dropDownValue), "PostMessage");
        return this;
    }

    public PostPage checkStateOfUniquePost(String state){
        checkIsElementVisible(getStateOfUniquePost(state), "StateOfUniquePost");
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
