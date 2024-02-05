package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(xpath = "//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select") // xpath = "//select"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement checkboxUnique;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }

    //check if we are on the Create Post Page
    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Create Post Page ", isElementDisplayed(inputTitle));
        return this;
    }

    @Step
    //fill title field
public CreatePostPage enterTitleInToInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    @Step
    public CreatePostPage enterTextInToInputBody(String bodyText) {
        enterTextIntoInput(inputBody, bodyText);
        return this;
    }

    @Step
    //select Text in dropDown
    public CreatePostPage selectTextInToDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    @Step
    //select value in dropDown
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }

    @Step
    public PostPage clickOnSaveNewButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage checkBoxUniquePost(String state) {
        setCheckBoxState(checkboxUnique, "Is this post unique", state);
        return this;
    }




}

