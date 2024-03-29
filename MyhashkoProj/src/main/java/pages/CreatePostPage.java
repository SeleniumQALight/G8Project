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
    @FindBy(tagName = "select") //  .//select[@id='select1']
    private WebElement dropDownSelectValue;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkBoxIsSelected;

    private static String SavedTextOfTitleFieldWhichWasEnteredOnCreatePostPage;
    private static String SavedTextOfBodyFieldWhichWasEnteredOnCreatePostPage;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    // check if we are on the Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        Assert.assertTrue("Invalid page - not Create Post page"
                , isElementDisplayed(inputTitle));
    return this;
    }

    // fill the title field
    public CreatePostPage enterTextIntoTitleField(String title) {
        enterTextIntoInput(inputTitle, title);
        this.SavedTextOfTitleFieldWhichWasEnteredOnCreatePostPage = title;
        return this;
    }

    public static String getEnteredTitle() {
        return SavedTextOfTitleFieldWhichWasEnteredOnCreatePostPage;
    }

    public CreatePostPage enterTextIntoInputBody(String Body) {
        enterTextIntoInput(inputBody, Body);
        this.SavedTextOfBodyFieldWhichWasEnteredOnCreatePostPage = Body;
        return this;
    }

    public static String getEnteredBody() {
        return SavedTextOfBodyFieldWhichWasEnteredOnCreatePostPage;
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

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage setStatusOfCheckBoxIsThisPostUnique(String checked) {
        setStatusOfCheckBoxIsThisPostUnique(checkBoxIsSelected, checked);
        return this;
    }

    private CreatePostPage unCheckCheckBoxIshisPostUnique() {
        setCheckBoxIsThisPostUniqueUnchecked(checkBoxIsSelected);
        return this;
    }

    private CreatePostPage checkCheckBoxIshisPostUnique() {
        setCheckBoxIsThisPostUniqueChecked(checkBoxIsSelected);
        return this;
    }

}
