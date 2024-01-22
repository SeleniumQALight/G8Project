package pages;

import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElement;

import java.util.ArrayList;
import java.util.List;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div[@class = 'd-flex justify-content-between']")
    private WebElement postTitleOnPostPage;

    @FindBy(xpath = ".//div[@class='body-content']/p[not(i)]")
    private WebElement postBodyOnPostPage;

    @FindBy(xpath = "//p//i[contains(text(), ' Note: This post was written for ')]")
    private WebElement postNoteOnPostPage;

    @FindBy(xpath = "//p[contains(text(), 'Is this post unique? : ')]")
    private WebElement postUniqueStateOnPostPage;

    private HeaderElement headerElement;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private List<WebElement> listSuccessMessageAfterCreatingPostMessages;

    private String listSuccessfullyMessagesLocator = ".//div[@class='alert alert-success text-center']";
    private String postNoteLocator = "//u[contains(text(),'%s')]";

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/post/[a-zA-Z0-9]*";
    }

    public PostPage checkIsRedirectToPostPage() {
        checkUrlWithPattern();
        //TODO Check element
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

    public PostPage checkPostTitleText(String text) {
        checkTextInElement(postTitleOnPostPage, text);
        logger.info("Post title is the same");
        return this;
    }

    public PostPage checkPostBodyText(String text) {
        checkTextInElement(postBodyOnPostPage, text);
        logger.info("Post body is the same");
        return this;
    }

    public PostPage checkPostNoteText(String text) {
        checkTextInElement(postNoteOnPostPage, text);
        logger.info("Post note is the same");
        return this;
    }

    //create method to check post unique state on post page
    public PostPage checkPostUniqueState(String state) {
        checkTextInElement(postUniqueStateOnPostPage,"Is this post unique? : " + state);
        logger.info("Post unique state is " + state);
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkSuccessCreatePostMessages(String messages) {
        // error1; error2 -> [error1, error2]
        String[] expectedMessages = messages.split(";");

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listSuccessfullyMessagesLocator), expectedMessages.length));

        Util.waitABit(1);
        Assert.assertEquals("Number of messages ", expectedMessages.length,
                listSuccessMessageAfterCreatingPostMessages.size()); // for checking all errors which are on the page

        ArrayList<String> actualErrors = new ArrayList<>();
        for (WebElement element : listSuccessMessageAfterCreatingPostMessages) {
            actualErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedMessages.length; i++) {
            softAssertions.assertThat(expectedMessages[i])
                    .as("Error " + i)
                    .isIn(actualErrors);
        }

        softAssertions.assertAll(); // check all assertion
        return this;
    }

    public PostPage checkIsNoteStateExpected(String valueOfPostUnique) {
        WebElement postNote = webDriver.findElement(By.xpath(String.format(postNoteLocator, valueOfPostUnique)));
        Assert.assertTrue("The post note is not in the expected state.", isElementDisplayed(postNote));
        return this;
    }
}
