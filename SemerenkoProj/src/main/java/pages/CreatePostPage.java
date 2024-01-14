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

    @FindBy(tagName = "select")  // .//select
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    // check if we are on the Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    // fill in the title field
    public CreatePostPage enterTextIntoInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String body) {
        enterTextIntoInput(inputBody, body);
        return this;
    }

    //select Text in dropDowm
    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    //select value in dropDown
    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }


    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
}
