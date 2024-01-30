package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement postTitle;

    @FindBy(xpath = "//div[@class='body-content']/p[not(i)]")
    private WebElement postBody;

    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//form[contains(@action, '/post/')]//button")
    private WebElement buttonSaveUpdates;

    private String postUniqueLocator = "//p[contains(text(),'Is this post unique? : %s')]";
    private String postNoteLocator = "//u[contains(text(),'%s')]";

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkCurrentUrlWithPattern();
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

    public PostPage checkIsPostUniqueStateConfirmExpectedValue(String valueOfPostUnique) {
        WebElement isPostUnique = webDriver.findElement(By.xpath(String.format(postUniqueLocator, valueOfPostUnique)));
        Assert.assertTrue("The post is not in the expected state or not found.", isElementDisplayed(isPostUnique));
        return this;
    }

    public PostPage checkIsPostNoteStateConfirmExpectedValue(String valueOfPostUnique) {
        WebElement postNote = webDriver.findElement(By.xpath(String.format(postNoteLocator, valueOfPostUnique)));
        Assert.assertTrue("The post is not in the expected state or not found.", isElementDisplayed(postNote));
        return this;
    }

    public PostPage checkPostTitleIsPresentOnPostPage(String expectedText){
        checkTextInElement(postTitle, expectedText);
        return this;
    }

    public PostPage checkPostBodyIsPresentOnPostPage(String expectedText){
        checkTextInElement(postBody, expectedText);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        //TODO
        return new MyProfilePage(webDriver);
    }

    public PostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return this;
    }

    public void enterTextIntoInputTitle(String text) {
        enterTextIntoInput(inputTitle, text);
    }

    public PostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }
}
