package pages;

import libs.Urls;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@id='post-title']")
    private WebElement titleField;
    @FindBy(xpath = "//textarea[@id='post-body']")
    private WebElement bodyContentField;
    @FindBy(xpath = "//select")
    private WebElement dropdown;
    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement saveNewPostButton;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    // check if we are on the Create Post page
    public CreatePostPage checkIsRedirectToCreatePostPage() {
        assertUrl(Urls.CREATE_POST_PAGE_URL);
        Assert.assertTrue("Invalid page - not Create Post Page", isElementDisplayed(titleField));
        return this;
    }

    public CreatePostPage enterTextInTitleInInputTitle(String title) {
        enterTextIntoInput(titleField, title);
        return this;
    }

    public CreatePostPage enterTextInBodyContentField(String text) {
        enterTextIntoInput(bodyContentField, text);
        return this;
    }

    public CreatePostPage selectValueInDropdown(String text) {
        selectValueInDropDown(dropdown, text);
        return this;
    }

    public CreatePostPage clickSaveNewPostButton() {
        clickOnElement(saveNewPostButton);
        return this;
    }




}