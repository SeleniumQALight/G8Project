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

    @FindBy(tagName = "select") // .//select
    private WebElement dropDownSelectValue;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSave;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check url
        Assert.assertTrue("Invalid page - not Create Post Page"
                , isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTitleIntoInput(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        enterTextIntoInput(inputBody, bodyText);
        return this;
    }

    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }


    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSave);
        return new PostPage(webDriver);
    }
}

