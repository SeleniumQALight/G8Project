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

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        //TODO check url
        //TODO check is Page title correct

        Assert.assertTrue("Invalid page", isElementDisplayed(inputTitle));
        return this;
    }
    //fill the title field

    public CreatePostPage enterTextInToInputTitle(String title) {
        //TODO enter text in to input Title
        enterTextInToInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String myrhorodskaBody) {
        enterTextInToInput(inputBody, myrhorodskaBody);
        return this;
    }

    //select text in drop down
    public CreatePostPage selectTextInDropDown(String textInDropDown) {
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
