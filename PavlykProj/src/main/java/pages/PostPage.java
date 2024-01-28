package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    private String noteLocator = ".//i[contains(text(),'Note: This post was written for')]/u[contains(text(),'%s')]";

    private String isThisPostUniqueLocator = ".//p[text()='Is this post unique? : %s']";

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    @FindBy(xpath = ".//p[contains(text(),'Is this post unique?')]/../following-sibling::div/p")
    private WebElement postContent;

    private HeaderElement headerElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDeletePost;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement editButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO check element
        return this;
    }

    private WebElement getIsThisPostUniqueElement (String сheckboxValue){
        return webDriver.findElement(By.xpath(String.format(isThisPostUniqueLocator, сheckboxValue)));
    }

    private WebElement getNoteElement (String dropdownValue){
        return webDriver.findElement(By.xpath(String.format(noteLocator, dropdownValue)));
    }

    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text) {
       checkTextInElement(successMessage, text);
        return this;
    }

    public PostPage checkPostWithTitleIsPresent(String post_title) {
        checkTextInElement(postTitle, post_title);
        return this;
    }

    public PostPage checkPostWithContentIsPresent(String post_content) {
        checkTextInElement(postContent, post_content);
        return this;
    }

    public PostPage checkIsThisPostUniqueTextPresent(String checkboxValue) {
        checkIsElementVisible(getIsThisPostUniqueElement(checkboxValue));
        return this;
    }

    public PostPage checkNoteTextPresent(String dropdownValue) {
        checkIsElementVisible(getNoteElement(dropdownValue));
        return this;
    }

    public EditPostPage clickOnEditButton() {
        clickOnElement(editButton);
        return new EditPostPage(webDriver);
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public MyProfilePage clickOnDeletePostButton() {
        clickOnElement(buttonDeletePost);
        return new MyProfilePage(webDriver);
    }
}
