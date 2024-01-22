package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CreatePostPage  extends ParentPage{
    @FindBy(xpath = "//input[@id='post-title']")
    private WebElement inputTitle;
    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select")  //xpath = "//select"
    private WebElement dropDownSelectValue;


    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkboxIsThisPostUnique;

    private static String SavedTextOfTitleFieldWhichWasEnteredOnCreatePostPage;
    private static String SavedTextOfBodyFieldWhichWasEnteredOnCreatePostPage;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        checkUrl();
        //TODO check is Page title correct

        Assert.assertTrue("Invalid page", isElementDisplayed(inputTitle));
        return this;
    }
    //fill the title field

    public CreatePostPage enterTextInToInputTitle(String title) {
        //TODO enter text in to input Title
        enterTextInToInput(inputTitle, title);
        this.SavedTextOfTitleFieldWhichWasEnteredOnCreatePostPage = title;
        return this;
    }
    public static String getEnteredTitle() {
        return SavedTextOfTitleFieldWhichWasEnteredOnCreatePostPage;
    }
   public CreatePostPage enterTextInToInputBody(String Body) {
       enterTextInToInput(inputBody, Body);
       this.SavedTextOfBodyFieldWhichWasEnteredOnCreatePostPage = Body;
        return this;
    }

    public static String getEnteredBody() {
        return SavedTextOfBodyFieldWhichWasEnteredOnCreatePostPage;
    }


    public CreatePostPage  setIsThisPostUniqueCheckBoxState (String state) {
        setCheckboxState(checkboxIsThisPostUnique ,state);
        return new CreatePostPage(webDriver);
    }


    //select text in drop down
    public CreatePostPage selectTextInDropDown (String textInDropDown) {
        //TODO select text in drop down
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }
    //select Value in drop down
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        //TODO select value in drop down
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }

    public PostPage clickOnSaveNewButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
