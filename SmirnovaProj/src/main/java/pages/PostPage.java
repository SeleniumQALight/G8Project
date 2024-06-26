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
    private WebElement titleText;
    @FindBy(xpath = "(//div[@class='body-content']//p)[2]")
    private WebElement bodyText;
    @FindBy(xpath = "//div[2][@class='body-content']")
    private WebElement bodyTextNote;
    private String uniquePostElement = "//p[contains(text(), 'Is this post unique? : %s')]";

    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    private HeaderElement headerElement;
    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();        //Assert.assertTrue("Invalid page - not Post Page"
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

    public PostPage checkTextInPostTitle(String text) {
        checkIsElementVisible(titleText);
        checkTextInElement(titleText, text);
        return this;
    }

    public PostPage checkTextInPostBody(String text) {
        checkIsElementVisible(bodyText);
        checkTextInElement(bodyText, text);
        return this;
    }

    public PostPage checkTextInPostBodyNote(String text) {
        checkIsElementVisible(bodyTextNote);
        checkTextInElement(bodyTextNote, text);
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public MyProfilePage clickOnDeletePostButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkPostUniqueState(String state) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(uniquePostElement, state))));
        return this;
    }

    public PostPage clickOnButtonEditPost() {
        checkIsElementVisible(buttonEdit);
        clickOnElement(buttonEdit);
        return this;
    }

}
