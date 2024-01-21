package pages;

import libs.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    private HeaderElement headerElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//h2")
    private WebElement titleAfterCreatePostText;

    @FindBy(xpath = "(//div[@class='body-content']//p)[2]")
    private WebElement bodyAfterCreatePostText;

    //private String uniquePostElement = "//div/p[contains(text(),'Is this post unique? : %s')]";
    private String checkBoxIsThisPostUnique = "//div/p[contains(text(),'Is this post unique? : %s')]";
    private String noteOnPostPageValue = "//i//u[contains(text(),'%s')]";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO check element
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

    public PostPage checkTextInTitleAfterCreatePost(String expectedText) {
        checkTextInElement(titleAfterCreatePostText, expectedText);
        logger.info("Title after create post is correct");
        return this;
    }

    public PostPage checkTextInBodyAfterCreatePost(String expectedText) {
        checkTextInElement(bodyAfterCreatePostText, expectedText);
        logger.info("Body after create post is correct");
        return this;
    }

    public HeaderElement getHeader() {
        return headerElement = new HeaderElement(webDriver);
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }


    public PostPage checkPostUniqueStateAfterCreatePost(String text) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(checkBoxIsThisPostUnique, text))));
        return this;
    }

    public PostPage checkOfNoteTextValue(String valueInDropDown) {
        checkIsElementVisible(webDriver.findElement(By.xpath(String.format(noteOnPostPageValue, valueInDropDown))));

        return this;
    }
}