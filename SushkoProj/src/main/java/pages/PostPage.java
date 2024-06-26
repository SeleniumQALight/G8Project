package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//p[contains(text(), 'Is this post unique?')]")
    private WebElement textIsThisPostUnique;

    @FindBy(xpath = ".//h2")
    private WebElement textPostTitle;

    @FindBy(xpath = ".//div[@class='body-content']/p[not(i)]")
    private WebElement textPostBodyContent;

    @FindBy(xpath = ".//div[@class='body-content']/p/i")
    private WebElement textPostNote;

    @FindBy(xpath = ".//button[@data-original-title = 'Delete']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//a[@class='text-primary mr-2']")
    private WebElement buttonEdit;

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

    public PostPage checkTextThisPostUnique(String checkboxIsSelected) {
        checkTextInElement(textIsThisPostUnique, "Is this post unique? : " + checkboxIsSelected);
        return this;
    }

    public PostPage checkTitleIsPresentInPost(String expectedText){
        checkTextInElement(textPostTitle, expectedText);
        return this;
    }

    public PostPage checkBodyContentIsPresentInPost(String expectedText){
        checkTextInElement(textPostBodyContent, expectedText);
        return this;
    }

    public PostPage checkCorrectNoteInPost(String expectedText){
        checkTextInElement(textPostNote, "Note: This post was written for " + expectedText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public CreatePostPage clickOnEditPostButton(){
        clickOnElement(buttonEdit);
        return new CreatePostPage(webDriver);
    }
}
