package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{

    @FindBy(xpath = ".//input[@id='post-title']")
private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select") // xpath = ".//select"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBoxUniquePost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }


    //check if we are on the Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
    Assert.assertTrue("Invalid page - not Create Post page", isElementDisplayed(inputTitle));
        return this;
    }

    //fill the title field
    public CreatePostPage enterTitleIntoInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
    enterTextIntoInput(inputBody, bodyText);
        return this;
    }

    //select Text in dropDown
    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    // select value in dropDown
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }


    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }



    public CreatePostPage setCheckBoxUniquePostChosen(String state) {
        setStateToCheckBox(checkBoxUniquePost, state);
        logger.info("CheckBox Unique Post was chosen");
        return this;
            }

}
