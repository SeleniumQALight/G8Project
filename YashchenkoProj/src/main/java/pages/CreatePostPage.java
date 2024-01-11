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

    @FindBy(tagName = "select") // xpath = ".//select[@id='select1']"
    private WebElement dropDownSelectValue;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement checkboxIsUnique;
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectedToCreatePostPage() {
        checkCurrentUrl();
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    // Create method for entering text in to input title
    public CreatePostPage enterTextInToInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    public CreatePostPage enterTextInToInputBody(String body) {
        enterTextIntoInput(inputBody, body);
        return this;
    }

    public CreatePostPage selectTextInDropDown(String text) {
        selectTextInDropDown(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDropDown(String value) {
        selectValueInDropDown(dropDownSelectValue, value);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }
    public CreatePostPage checkIsCheckboxIsUniquePresent() {
        Assert.assertTrue("Checkbox is not present", isElementDisplayed(checkboxIsUnique));
        return this;
    }

    public CreatePostPage checkCheckboxIsUnique() {
        checkIsCheckboxIsUniquePresent();
        checkCheckbox(checkboxIsUnique);
        return this;
    }
    public CreatePostPage uncheckCheckboxIsUnique() {
        checkIsCheckboxIsUniquePresent();
        uncheckCheckbox(checkboxIsUnique);
        return this;
    }

    public CreatePostPage selectIsUniqueCheckboxUsingStringValue(String state) {
        switch (state.toLowerCase()) {
            case "check":
                checkCheckboxIsUnique();
                break;
            case "uncheck":
                uncheckCheckboxIsUnique();
                break;
            default:
                Assert.fail("State should be 'check' or 'uncheck'");
        }
        return this;
    }
}
