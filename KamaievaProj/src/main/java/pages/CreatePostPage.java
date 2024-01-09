package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = "//input[@id= 'post-title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//textarea[@id= 'post-body']")
    private WebElement inputBody;

    @FindBy(tagName = "select") //xpath = "//select"
    private WebElement dropdownSelectValue;

    @FindBy(xpath = "//button[contains(text(),'Save New Post')]")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@name= 'uniquePost']")
    private WebElement checkboxUniquePost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    //Check if we are on the Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        //TODO check url
        Assert.assertTrue("Invalidpage - Create Post page is not opened", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String bodyText) {
        enterTextIntoInput(inputBody, bodyText);
        return this;
    }

    // Select text in dropdown
    public CreatePostPage selectTextInDropdown(String textInDropdown) {
        selectTextInDropdown(dropdownSelectValue, textInDropdown);
        return this;
    }

    //Select Value in dropdown
    public CreatePostPage selectValueInDropdown(String valueInDropdown) {
        selectValueInDropdown(dropdownSelectValue, valueInDropdown);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage checkboxUniquePost(String state){
        setCheckboxState(checkboxUniquePost, "Is this post unique", state);
        return this;
    }
}
