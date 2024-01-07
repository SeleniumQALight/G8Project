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

    @FindBy(tagName = "select") // xpath = ".//select"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkbox;
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    //check if we are on create post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        // TODO check url
        Assert.assertTrue("Invalid page  --- not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }

    //enter text into input title

    public CreatePostPage enterTextIntoInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        enterTextIntoInput(inputBody, bodyText);
        return this;
    }

    //select Text from dropdown
    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    //select Value from dropdown
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage setCheckbox(String status) {
        setCheckbox(status,checkbox);
        return this;
    }

}
