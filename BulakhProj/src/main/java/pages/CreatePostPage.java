package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreatePostPage extends ParentPage {
    CommonActionsWithElements commonActions = new CommonActionsWithElements(webDriver);


    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select") // xpath =".//select"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    // check if we are on Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Create Post page"
                , isElementDisplayed(inputTitle));
        return this;
    }

    //fill the title field
    public CreatePostPage enterTitleInToInputTitle(String title) {
        enterTextInToInput(inputTitle, title);
        return this;
    }


    public CreatePostPage enterTextInToInputBody(String bodyText) {
        enterTextInToInput(inputBody, bodyText);
        return this;
    }

    // select Text in DropDown
    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    // select Value in DropDown
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage setCheckboxState(String state) {
        setCheckboxState(dropDownSelectValue, state);
        return this;

    }


    }


