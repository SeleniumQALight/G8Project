package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParrentPage {
    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select")   // xpath = ".//select[@id='select1']"
    private WebElement dropDownSelectValue;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;
    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement uniquePostCheckbox;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    // check if we are on the Create Post Page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        // TODO check url
        Assert.assertTrue("Invalid page - not Create Post Page"
                , isElementDisplayed(inputTitle));

        return this;
    }
    // fill the title field

    public CreatePostPage enterTextInToInputTitle(String title) {
        enterTextInToInput(inputTitle, title);
        return this;
    }


    public CreatePostPage enterTextInToInputBody(String bodyText) {
        enterTextInToInput(inputBody, bodyText);
        return this;
    }


    // select text from dropdown


    public CreatePostPage selectTextInDropDown(String textInDropDown) {
        selectTextInDropDown(dropDownSelectValue, textInDropDown);
        return this;
    }

    //select value from dropdown

    public CreatePostPage selectValueInDropDown(String valueInDropDown) {
        selectValueInDropDown(dropDownSelectValue, valueInDropDown);
        return this;
    }


    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }


    public CreatePostPage setUniquePostCheckboxSelected(String status) {
        setStatusForCheckbox(uniquePostCheckbox, status);
        return this;
    }


}
