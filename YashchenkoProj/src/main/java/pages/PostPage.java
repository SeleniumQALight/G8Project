package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = "//i")
    private WebElement note;

    @FindBy(xpath = "//i//u")
    private WebElement noteValue;

    private String isThisPostUniqueValue = "//p[contains(text(), 'Is this post unique? : %s')]";
    private String createdPostTitle = "//h2[text()='%s']";
    private String createdPostBody = "//p[text()='%s']";

    @FindBy(xpath = "//button[@data-original-title='Delete']")
    private WebElement trashIcon;

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement editIcon;

    @FindBy(xpath = "//h2")
    private WebElement titleOfCreatedPost;

    private HeaderElement headerElement;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectedToPostPage() {
        checkCurrentUrlWithPattern();
        // TODO check some element on page
        return this;
    }

    @Step
    public PostPage checkIsSuccessMessageDisplayed() {
        checkIsElementVisible(successMessage);
        return this;
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
    public PostPage checkIsThisPostUniqueValuePresent(String value) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(isThisPostUniqueValue, value))));
        return this;
    }

    @Step
    public PostPage checkCreatedPostTitle(String value) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(createdPostTitle, value))));
        return this;
    }

    @Step
    public PostPage checkCreatedPostBody(String value) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(createdPostBody, value))));
        return this;
    }

    @Step
    public PostPage checkIsNoteAndNoteValuePresent() {
        checkIsElementVisible(note);
        checkIsElementVisible(noteValue);
        return this;
    }

    @Step
    public PostPage checkValueOfNote(String value) {
        checkTextInElement(noteValue, value);
        return this;
    }

    @Step
    public MyProfilePage clickOnTrashIcon() {
        clickOnElement(trashIcon);
        return new MyProfilePage(webDriver);
    }

    @Step
    public EditPostPage clickOnEitIcon() {
        clickOnElement(editIcon);
        return new EditPostPage(webDriver);
    }
}
