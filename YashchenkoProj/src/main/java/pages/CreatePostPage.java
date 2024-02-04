package pages;

import io.qameta.allure.Step;
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

    @Step
    public CreatePostPage checkIsRedirectedToCreatePostPage() {
        checkCurrentUrl();
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(inputTitle));
        return this;
    }

    @Step
    // Create method for entering text in to input title
    public CreatePostPage enterTextInToInputTitle(String title) {
        enterTextIntoInput(inputTitle, title);
        return this;
    }

    @Step
    public CreatePostPage enterTextInToInputBody(String body) {
        enterTextIntoInput(inputBody, body);
        return this;
    }

    @Step
    public CreatePostPage selectTextInDropDown(String text) {
        selectTextInDropDown(dropDownSelectValue, text);
        return this;
    }

    @Step
    public CreatePostPage selectValueInDropDown(String value) {
        selectValueInDropDown(dropDownSelectValue, value);
        return this;
    }

    @Step
    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    @Step
    public CreatePostPage checkIsCheckboxIsUniquePresent() {
        Assert.assertTrue("Checkbox is not present", isElementDisplayed(checkboxIsUnique));
        return this;
    }

    @Step
    public CreatePostPage checkCheckboxIsUnique() {
        checkIsCheckboxIsUniquePresent();
        checkCheckbox(checkboxIsUnique);
        return this;
    }

    @Step
    public CreatePostPage uncheckCheckboxIsUnique() {
        checkIsCheckboxIsUniquePresent();
        uncheckCheckbox(checkboxIsUnique);
        return this;
    }

    @Step
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
        logger.info("Checkbox was changed to: " + state);
        return this;
    }
}
