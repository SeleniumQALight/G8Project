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

    @FindBy(tagName = "select")// xpath = ".//.select"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@type = 'checkbox']")
    private WebElement checkBox;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    //check if we are on the Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check url
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    //fill the title field
    public CreatePostPage enterTitleIntoInputTitle(String title) {
        enterTextInToInput(inputTitle, title);
        return this;
    }

    //fill the body field
    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        enterTextInToInput(inputBody, bodyText);
        return this;
    }

    //select Text in dropDown
    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    //select Value in dropDown
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectCheckboxUniqueState(String state) {
        setCheckboxState(checkBox,"Is this post unique?", state);
        return this;
    }
}
