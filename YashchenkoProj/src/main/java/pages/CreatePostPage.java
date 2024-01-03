package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select") // xpath = ".//select[@id='select1']"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectedToCreatePostPage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    // Create method for entering text in to input title
    public CreatePostPage enterTextInToInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String body) {
        enterTextIntoInput(inputBody, body);
        return this;
    }

    public CreatePostPage selectTextInDropDown(String text) {
        selectTextInDropDown(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDropDown(String value) {
        selectValueInDropDown(dropDownSelectValue, value);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
